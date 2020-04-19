package ru.inbox.savinov_vu.app.tasks.task.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.config.AbstractSpringBootTest;
import ru.inbox.savinov_vu.test_helpers.data.init.MockMvcBuilderInitializer;
import ru.inbox.savinov_vu.test_helpers.data.init.RegExpTaskInitializer;
import org.hamcrest.Matchers;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@Slf4j
class RegExpTaskControllerTest extends AbstractSpringBootTest {

  private static final String CHECK_URL = "/v1/tasks/regexptask/check/";

  @Resource
  private RegExpTaskInitializer regExpTaskInitializer;

  @Resource
  private MockMvcBuilderInitializer mockInit;

  @Resource
  private MockMvc mockMvc;

  RegExpTask regExpTask;


  @BeforeEach
  void check() {
    regExpTask = regExpTaskInitializer.initOne();
  }


  @Test
  void check_valid() throws Exception {
    ResultActions result = mockMvc
      .perform(mockInit.putRequest(CHECK_URL + regExpTask.getId(), regExpTask.getAuthor())
        .content(regExpTask.getAnswers().get(0)));

    result.andExpect(status().isOk())
      .andExpect(jsonPath("$.success", Matchers.equalTo(true)));

  }


  @Test
  void check_invalid() throws Exception {
    ResultActions result = mockMvc
      .perform(mockInit.putRequest(CHECK_URL + regExpTask.getId(), regExpTask.getAuthor())
        .content("null"));

    result.andExpect(status().isOk())
      .andExpect(jsonPath("$.success", Matchers.equalTo(false)));

  }
}
