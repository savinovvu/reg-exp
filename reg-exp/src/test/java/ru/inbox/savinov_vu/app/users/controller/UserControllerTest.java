package ru.inbox.savinov_vu.app.users.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.model.User_;
import ru.inbox.savinov_vu.config.AbstractSpringBootTest;
import ru.inbox.savinov_vu.test_helpers.data.init.MockMvcBuilderInitializer;
import ru.inbox.savinov_vu.test_helpers.data.init.UserInitializer;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



class UserControllerTest extends AbstractSpringBootTest {

  private static final String FILTERED_GET_PATH = "/v1/users/user/filter";

  @Resource
  private MockMvc mockMvc;

  @Resource
  private UserInitializer userInitializer;

  @Resource
  private MockMvcBuilderInitializer mockInit;

  private List<User> users;


  @BeforeEach
  void initDataBase() {
    users = userInitializer.initOneList();
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
    List<User> enabled = users.stream().filter(v -> v.getEnabled()).collect(Collectors.toList());
    User user = enabled.get(0);
    ResultActions result = mockMvc.perform(mockInit.getRequest(FILTERED_GET_PATH, user)
      .param("size", "50")
    );

    result
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.items.length()", Matchers.equalTo(enabled.size())));
  }


  @Test
  void getFistPage() throws Exception {
    List<User> enabled = users.stream().filter(v -> v.getEnabled()).collect(Collectors.toList());
    User user = enabled.get(0);
    ResultActions result = mockMvc.perform(mockInit.getRequest(FILTERED_GET_PATH, user)
      .param("size", "10")
    );

    result
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.items.length()", Matchers.equalTo(10)));
  }


  @Test
  void getById() throws Exception {
    List<User> enabled = users.stream().filter(v -> v.getEnabled()).collect(Collectors.toList());
    User user = enabled.get(0);
    ResultActions result = mockMvc.perform(mockInit.getRequest(FILTERED_GET_PATH, user)
      .param(User_.ID, String.valueOf(user.getId())));

    result
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.items.length()", Matchers.equalTo(1)))
      .andExpect(jsonPath("$.items[0].id", Matchers.equalTo(String.valueOf(user.getId()))));
  }


  @Test
  void getFirstName() throws Exception {
    List<User> enabled = users.stream().filter(v -> v.getEnabled()).collect(Collectors.toList());
    User user = enabled.get(0);
    ResultActions result = mockMvc.perform(mockInit.getRequest(FILTERED_GET_PATH, user)
      .param(User_.FIRST_NAME, "firstName1"));

    result
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.items.length()", Matchers.equalTo(2)))
      .andExpect(jsonPath("$.items[0].firstName", Matchers.equalTo("firstName1")))
    ;
  }


  @Test
  void getInvalidParam() throws Exception {
    List<User> enabled = users.stream().filter(v -> v.getEnabled()).collect(Collectors.toList());
    User user = enabled.get(0);
    ResultActions result = mockMvc.perform(mockInit.getRequest(FILTERED_GET_PATH, user)
      .param(User_.BIRTH_DATE, "sometext"));

    result
      .andExpect(status().isBadRequest());
  }


}
