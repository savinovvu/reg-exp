package ru.inbox.savinov_vu.core.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.config.AbstractSpringBootTest;
import ru.inbox.savinov_vu.core.security.service.SecurityService;
import ru.inbox.savinov_vu.core.security.jwt.dto.LoginDto;
import ru.inbox.savinov_vu.core.security.jwt.dto.SignUpDto;
import ru.inbox.savinov_vu.test_helpers.data.factories.jwt.LoginDtoFactory;
import ru.inbox.savinov_vu.test_helpers.data.factories.jwt.SignUpDtoFactory;
import ru.inbox.savinov_vu.test_helpers.data.factories.user.UserFactory;
import ru.inbox.savinov_vu.test_helpers.data.init.UserInitializer;

import javax.annotation.Resource;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.inbox.savinov_vu.test_helpers.data.factories.constant.Constants.LONG_EMPTY_STRING;
import static ru.inbox.savinov_vu.test_helpers.data.factories.constant.Constants.TOO_LONG_WORD;



@Slf4j
class SecurityControllerTest extends AbstractSpringBootTest {

  private static final String SIGN_IN_PATH = "/v1/sign-in";

  private static final String SIGN_UP_PATH = "/v1/sign-up";


  @Resource
  private JacksonTester<LoginDto> loginTester;

  @Resource
  private JacksonTester<SignUpDto> signUpTester;

  @Resource
  private MockMvc mockMvc;

  @Resource
  private UserInitializer userInitializer;

  @Resource
  private SecurityService securityService;


  @Nested
  @DisplayName("sign in tests")
  class SignInTest {


    private LoginDto validLoginDto;

    private User user;


    @BeforeEach
    void initDataBase() {
      User rawUser = UserFactory.getOne();
      validLoginDto = LoginDtoFactory.getFromUser(rawUser);
      user = userInitializer.initOne(rawUser);
    }


    @ParameterizedTest
    @ValueSource(
      strings = {"",
        " ",
        "asd",
        "asdf",
        TOO_LONG_WORD,
        LONG_EMPTY_STRING,
        LONG_EMPTY_STRING + "a",
        LONG_EMPTY_STRING + "asdf"}
    )
    @NullSource
    void login_invalid_input(String s) throws Exception {
      LOG.info("input with param: \"" + s + "\"");
      LoginDto loginDto = new LoginDto(s, s);

      ResultActions result = mockMvc.perform(post(SIGN_IN_PATH).content(loginTester.write(loginDto).getJson())
        .contentType(MediaType.APPLICATION_JSON));

      result.andExpect(status().isBadRequest());
    }


    @Test
    void login_invalidCredentials_unauthorized() throws Exception {
      LoginDto loginDto = new LoginDto("asdfasdfasdf", "asdfasdfasdf");

      ResultActions result = mockMvc.perform(post(SIGN_IN_PATH).content(loginTester.write(loginDto).getJson())
        .contentType(MediaType.APPLICATION_JSON));

      result.andExpect(status().isUnauthorized());
    }


    @Test
    void login_validCredentials_success() throws Exception {
      ResultActions result = mockMvc.perform(post(SIGN_IN_PATH).content(loginTester.write(validLoginDto).getJson())
        .contentType(MediaType.APPLICATION_JSON));

      result
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.fullName", equalTo(user.getFullName())))
        .andExpect(jsonPath("$.id", equalTo(user.getId())))
        .andExpect(jsonPath("$.token", notNullValue()))
      ;
    }

  }

  @Nested
  @DisplayName("sign up tests")
  class SignUpTest {


    @ParameterizedTest
    @MethodSource("ru.inbox.savinov_vu.core.security.jwt.controller.SecurityControllerTest#getInvalidSignUpDto")
    void signUp_invalid_input(SignUpDto signUpDto, String message) throws Exception {
      LOG.info(message);
      userInitializer.deleteAll();
      ResultActions result = mockMvc
        .perform(post(SIGN_UP_PATH)
          .content(signUpTester.write(signUpDto).getJson())
          .contentType(MediaType.APPLICATION_JSON));

      result
        .andExpect(status().isBadRequest())
      ;

    }


    @Test
    void signUp_valid_input() throws Exception {
      userInitializer.deleteAll();
      SignUpDto validDto = SignUpDtoFactory.of();

      ResultActions result = mockMvc
        .perform(post(SIGN_UP_PATH)
          .content(signUpTester.write(validDto).getJson())
          .contentType(MediaType.APPLICATION_JSON));

      result
        .andExpect(status().isOk())
      ;

      UserDetails userDetails = securityService.loadUserByUsername(validDto.getLogin());
      assertEquals(validDto.getLogin(), userDetails.getUsername(), "user must be saved");
    }

  }


  public static Stream<Arguments> getInvalidSignUpDto() {
    return Stream.of(
      Arguments.of(SignUpDtoFactory.of().setFirstName(null), "firstName is null param"),
      Arguments.of(SignUpDtoFactory.of().setFirstName(""), "firstName is empty param"),
      Arguments.of(SignUpDtoFactory.of().setFirstName(" "), "firstName is param with space"),
      Arguments.of(SignUpDtoFactory.of().setFirstName(LONG_EMPTY_STRING), "firstName lastNameis long empty string"),
      Arguments.of(SignUpDtoFactory.of().setFirstName(TOO_LONG_WORD), "firstName is too long word"),

      Arguments.of(SignUpDtoFactory.of().setLastName(null), "lastName is null param"),
      Arguments.of(SignUpDtoFactory.of().setLastName(""), "lastName is empty param"),
      Arguments.of(SignUpDtoFactory.of().setLastName(" "), "lastName is param with space"),
      Arguments.of(SignUpDtoFactory.of().setLastName(LONG_EMPTY_STRING), "lastName is long empty string"),
      Arguments.of(SignUpDtoFactory.of().setLastName(TOO_LONG_WORD), "lastName is too long word"),

      Arguments.of(SignUpDtoFactory.of().setLogin(null), "login is null param"),
      Arguments.of(SignUpDtoFactory.of().setLogin(""), "login is empty param"),
      Arguments.of(SignUpDtoFactory.of().setLogin(" "), "login is param with space"),
      Arguments.of(SignUpDtoFactory.of().setLogin(LONG_EMPTY_STRING), "login is long empty string"),
      Arguments.of(SignUpDtoFactory.of().setLogin(TOO_LONG_WORD), "login is too long word"),
      Arguments.of(SignUpDtoFactory.of().setLogin(LONG_EMPTY_STRING + "asdf"), "login is long empty string with param"),

      Arguments.of(SignUpDtoFactory.of().setEmail(null), "email is null param"),
      Arguments.of(SignUpDtoFactory.of().setEmail(""), "email is empty param"),
      Arguments.of(SignUpDtoFactory.of().setEmail(" "), "email is param with space"),
      Arguments.of(SignUpDtoFactory.of().setEmail(LONG_EMPTY_STRING), "email is long empty string"),
      Arguments.of(SignUpDtoFactory.of().setEmail(TOO_LONG_WORD), "email is too long word"),
      Arguments.of(SignUpDtoFactory.of().setEmail(LONG_EMPTY_STRING + "asdf"), "email is long empty string with param"),

      Arguments.of(SignUpDtoFactory.of().setPassword(null).setRepeatPassword(null), "password and repeatPassword is  null param"),
      Arguments.of(SignUpDtoFactory.of().setPassword("").setRepeatPassword(""), "password and repeatPassword is empty param"),
      Arguments.of(SignUpDtoFactory.of().setPassword(" ").setRepeatPassword(" "), "password and repeatPassword is param with space"),
      Arguments.of(SignUpDtoFactory.of().setPassword(LONG_EMPTY_STRING).setRepeatPassword(LONG_EMPTY_STRING), "password and repeatPassword is long empty string"),
      Arguments.of(SignUpDtoFactory.of().setPassword(TOO_LONG_WORD).setRepeatPassword(TOO_LONG_WORD), "password and repeatPassword is too long word"),
      Arguments.of(SignUpDtoFactory.of().setPassword(LONG_EMPTY_STRING + "asdf").setRepeatPassword(LONG_EMPTY_STRING + "asdf"), "password and repeatPassword is long empty string with param"),
      Arguments.of(SignUpDtoFactory.of().setPassword("sdf").setRepeatPassword("mnb"), "password and repeatPassword is difference")
    );
  }

}



