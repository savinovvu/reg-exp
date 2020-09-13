package ru.inbox.savinov_vu.app.tasks.level.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.inbox.savinov_vu.app.tasks.level.dto.RegExpLevelDto;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.app.tasks.level.service.RegExpLevelService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.inbox.savinov_vu.test_helpers.data.factories.RegExpLevelFactory.getRegExpLevelWithId;


@ExtendWith(MockitoExtension.class)
class RegExpLevelControllerTest {

  private RegExpLevelController subject;

  @Mock
  private RegExpLevelService levelService;

  private MockMvc mvc;

  @BeforeEach
  void setUp() {
    subject = new RegExpLevelController(levelService);
    mvc = MockMvcBuilders.standaloneSetup(subject).build();
  }

  @Test
  void getAll() throws Exception {
    when(levelService.findAllForUser(1))
      .thenReturn(List.of(
        new RegExpLevelDto("1", "descr", "12", false, 50),
        new RegExpLevelDto().setId("2")
      ));
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/v1/tasks/regexplevel")
      .header("id", 1);

    mvc.perform(request)
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(2))
      .andExpect(jsonPath("$[0].id").value("1"))
      .andExpect(jsonPath("$[0].description").value("descr"))
      .andExpect(jsonPath("$[0].number").value("12"))
      .andExpect(jsonPath("$[0].solve").value(false))
      .andExpect(jsonPath("$[0].score").value(50))
      .andExpect(jsonPath("$[1].id").value("2"));
  }


  @Test
  void getById() throws Exception {
    RegExpLevel level = getRegExpLevelWithId(1);
    when(levelService.findById(1)).thenReturn(level);

    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/v1/tasks/regexplevel/1");

    mvc.perform(request)
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id").value(level.getId()))
      .andExpect(jsonPath("$.number").value(level.getNumber()))
      .andExpect(jsonPath("$.enDescription").value(level.getEnDescription()))
      .andExpect(jsonPath("$.ruDescription").value(level.getRuDescription()))
      .andExpect(jsonPath("$.enabled").value(level.isEnabled()))
      .andExpect(jsonPath("$.score").value(level.getScore()));
  }

}
