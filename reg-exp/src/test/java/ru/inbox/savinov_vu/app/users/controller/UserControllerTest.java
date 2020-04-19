package ru.inbox.savinov_vu.app.users.controller;

import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ru.inbox.savinov_vu.app.users.dto.UserDto;
import ru.inbox.savinov_vu.app.users.dto.UserMapper;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.model.User_;
import ru.inbox.savinov_vu.config.AbstractSpringBootTest;
import ru.inbox.savinov_vu.test_helpers.data.factories.user.UserDtoFactory;
import ru.inbox.savinov_vu.test_helpers.data.init.MockMvcBuilderInitializer;
import ru.inbox.savinov_vu.test_helpers.data.init.UserInitializer;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.inbox.savinov_vu.test_helpers.data.factories.constant.Constants.LONG_EMPTY_STRING;
import static ru.inbox.savinov_vu.test_helpers.data.factories.constant.Constants.TOO_LONG_WORD;



@Slf4j
class UserControllerTest extends AbstractSpringBootTest {

  private static final String FILTERED_GET_PATH = "/v1/users/user/filter";

  private static final String COMMON_PATH = "/v1/users/user";

  @Resource
  private MockMvc mockMvc;

  @Resource
  private UserInitializer userInitializer;

  @Resource
  private MockMvcBuilderInitializer mockInit;

  @Resource
  private JacksonTester<UserDto> userDtoJackson;

  @Resource
  private UserMapper userMapper;

  private List<User> users;

  private User user;


  @BeforeEach
  void initDataBase() {
    users = userInitializer.initOneList();
    user = userInitializer.getEnabledUserFromExisting();
  }


  @Test
  void withoutAuthentication() throws Exception {
    ResultActions result = mockMvc.perform(get(FILTERED_GET_PATH));

    result
      .andExpect(status().isFound())
      .andExpect(redirectedUrl("/login"));
  }


  @Test
  void getAllEnabled() throws Exception {
    List<User> enabled = userInitializer.getEnabledUsersFromExisting();
    ResultActions result = mockMvc.perform(mockInit.getRequest(FILTERED_GET_PATH, user)
      .param("size", "50")
    );

    result
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.items.length()", Matchers.equalTo(enabled.size())));
  }


  @Test
  void getFistPage() throws Exception {
    ResultActions result = mockMvc.perform(mockInit.getRequest(FILTERED_GET_PATH, user)
      .param("size", "10")
    );

    result
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.items.length()", Matchers.equalTo(10)));
  }


  @Test
  void getById() throws Exception {
    ResultActions result = mockMvc.perform(mockInit.getRequest(FILTERED_GET_PATH, user)
      .param(User_.ID, String.valueOf(user.getId())));

    result
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.items.length()", Matchers.equalTo(1)))
      .andExpect(jsonPath("$.items[0].id", Matchers.equalTo(String.valueOf(user.getId()))));
  }


  @Test
  void getFirstName() throws Exception {
    ResultActions result = mockMvc.perform(mockInit.getRequest(FILTERED_GET_PATH, user)
      .param(User_.FIRST_NAME, "firstName1"));

    result
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.items.length()", Matchers.equalTo(2)))
      .andExpect(jsonPath("$.items[0].firstName", Matchers.equalTo("firstName1")))
    ;
  }


  @Test
  void get_invalidParam() throws Exception {
    ResultActions result = mockMvc.perform(mockInit.getRequest(FILTERED_GET_PATH, user)
      .param(User_.BIRTH_DATE, "sometext"));

    result
      .andExpect(status().isBadRequest());
  }


  @Test
  void delete() throws Exception {
    Integer id = user.getId();
    UserDto userDto = userMapper.mapEntityToDto(user);
    ResultActions result = mockMvc
      .perform(mockInit.deleteRequest(COMMON_PATH + "/" + id, user)
        .content(userDtoJackson.write(userDto).getJson())
        .contentType(MediaType.APPLICATION_JSON));

    result
      .andExpect(status().isOk());

    User updatedUser = userInitializer.getById(id);
    assertEquals(false, updatedUser.getEnabled());
  }


  @Test
  void update_validParam() throws Exception {
    Integer id = user.getId();
    user.setLastName("qweasdzxc");
    UserDto userDto = userMapper.mapEntityToDto(user);
    ResultActions result = mockMvc
      .perform(mockInit.putRequest(COMMON_PATH, user)
        .content(userDtoJackson.write(userDto).getJson())
        .contentType(MediaType.APPLICATION_JSON));

    result
      .andExpect(status().isOk());

    User updatedUser = userInitializer.getById(id);
    assertEquals(userDto.getLastName(), updatedUser.getLastName());
  }


  @ParameterizedTest
  @MethodSource("getInvalidUserDto")
  void update_invalidParam(UserDto userDto, String message) throws Exception {
    LOG.info(message);
    ResultActions result = mockMvc
      .perform(mockInit.putRequest(COMMON_PATH, user)
        .content(userDtoJackson.write(userDto).getJson())
        .contentType(MediaType.APPLICATION_JSON));

    result
      .andExpect(status().isBadRequest())
    ;
  }


  public static Stream<Arguments> getInvalidUserDto() {
    return Stream.of(
      Arguments.of(UserDtoFactory.of().setFirstName(null), "firstName is null param"),
      Arguments.of(UserDtoFactory.of().setFirstName(""), "firstName is empty param"),
      Arguments.of(UserDtoFactory.of().setFirstName(" "), "firstName is param with space"),
      Arguments.of(UserDtoFactory.of().setFirstName(LONG_EMPTY_STRING), "firstName lastNameis long empty string"),
      Arguments.of(UserDtoFactory.of().setFirstName(TOO_LONG_WORD), "firstName is too long word"),

      Arguments.of(UserDtoFactory.of().setLastName(null), "lastName is null param"),
      Arguments.of(UserDtoFactory.of().setLastName(""), "lastName is empty param"),
      Arguments.of(UserDtoFactory.of().setLastName(" "), "lastName is param with space"),
      Arguments.of(UserDtoFactory.of().setLastName(LONG_EMPTY_STRING), "lastName is long empty string"),
      Arguments.of(UserDtoFactory.of().setLastName(TOO_LONG_WORD), "lastName is too long word"),

      Arguments.of(UserDtoFactory.of().setLogin(null), "login is null param"),
      Arguments.of(UserDtoFactory.of().setLogin(""), "login is empty param"),
      Arguments.of(UserDtoFactory.of().setLogin(" "), "login is param with space"),
      Arguments.of(UserDtoFactory.of().setLogin(LONG_EMPTY_STRING), "login is long empty string"),
      Arguments.of(UserDtoFactory.of().setLogin(TOO_LONG_WORD), "login is too long word"),
      Arguments.of(UserDtoFactory.of().setLogin(LONG_EMPTY_STRING + "asdf"), "login is long empty string with param"),

      Arguments.of(UserDtoFactory.of().setEmail(null), "email is null param"),
      Arguments.of(UserDtoFactory.of().setEmail(""), "email is empty param"),
      Arguments.of(UserDtoFactory.of().setEmail(" "), "email is param with space"),
      Arguments.of(UserDtoFactory.of().setEmail(LONG_EMPTY_STRING), "email is long empty string"),
      Arguments.of(UserDtoFactory.of().setEmail(TOO_LONG_WORD), "email is too long word"),
      Arguments.of(UserDtoFactory.of().setEmail(LONG_EMPTY_STRING + "asdf"), "email is long empty string with param")
    );
  }
}
