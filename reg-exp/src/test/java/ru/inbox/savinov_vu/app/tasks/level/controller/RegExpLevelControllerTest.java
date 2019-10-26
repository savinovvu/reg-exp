package ru.inbox.savinov_vu.app.tasks.level.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.app.tasks.level.repository.RegExpLevelRepository;
import ru.inbox.savinov_vu.app.tasks.level.service.RegExpLevelService;
import ru.inbox.savinov_vu.app.users.repository.UserRepository;
import ru.inbox.savinov_vu.app.users.service.UserService;
import ru.inbox.savinov_vu.config.AbstractControllerTest;
import ru.inbox.savinov_vu.stumb.UserFactory;
import ru.inbox.savinov_vu.utils.WebTestHelper;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static ru.inbox.savinov_vu.stumb.RegExpLevelFactory.getRegExpLevel;
import static ru.inbox.savinov_vu.stumb.RegExpLevelFactory.getRegExpLevelRepositoryMock;
import static ru.inbox.savinov_vu.stumb.RegExpLevelFactory.getRegExpLevelServiceMock;
import static ru.inbox.savinov_vu.stumb.RegExpLevelFactory.getRegExpLevelWithId;



class RegExpLevelControllerTest extends AbstractControllerTest {

  @Resource
  private WebTestHelper webTestHelper;

  @Resource
  private RegExpLevelService regExpLevelService;


  @Test
  void getAll() {
    String result = webTestHelper.performRequest(get("/v1/tasks/regexplevel"));
    List<RegExpLevel> all = regExpLevelService.findAll(1);
    String expected = webTestHelper.objectToJson(all);
    assertEquals("json must be right", expected, result);
  }


  @Test
  void getById() {
    String result = webTestHelper.performRequest(get("/v1/tasks/regexplevel/1"));
    RegExpLevel regExpLevel = regExpLevelService.findById(1);
    String expected = webTestHelper.objectToJson(regExpLevel);
    assertEquals("json must be right", expected, result);
  }


  @Test
  void getByNumber() {
    String result = webTestHelper.performRequest(get("/v1/tasks/regexplevel/byNumber/1"));
    RegExpLevel regExpLevel = regExpLevelService.findByNumber(1);
    String expected = webTestHelper.objectToJson(regExpLevel);
    assertEquals("json must be right", expected, result);
  }


  @Test
  void create() {
    RegExpLevel regExpLevel = getRegExpLevel();
    String result = webTestHelper.performRequest(post("/v1/tasks/regexplevel"), regExpLevel);
    String expected = webTestHelper.objectToJson(regExpLevel.setId(1));
    assertEquals("json must be right", expected, result);
  }


  @Test
  void update() {
    RegExpLevel regExpLevel = getRegExpLevelWithId(1);
    String result = webTestHelper.performRequest(put("/v1/tasks/regexplevel"), regExpLevel);
    String expected = webTestHelper.objectToJson(regExpLevel);
    assertEquals("json must be right", expected, result);
  }


  @Test
  void remove() {
    String result = webTestHelper.performRequest(delete("/v1/tasks/regexplevel/1"));
    assertEquals("json must be right", "true", result);
  }


  @TestConfiguration
  public static class RegExpLevelServiceTestContextConfiguration {


    @Bean
    public RegExpLevelController regExpLevelController() {
      return new RegExpLevelController(regExpLevelService(), userService());
    }


    @Bean
    public RegExpLevelService regExpLevelService() {
      RegExpLevelService regExpLevelServiceMock = getRegExpLevelServiceMock();
      return regExpLevelServiceMock;
    }


    @Bean
    public UserService userService() {
      UserService mock = UserFactory.getUserServiceMock();
      return mock;
    }


    @Bean
    public UserRepository userRepository() {
      UserRepository userRepositoryMock = UserFactory.getUserRepositoryMock();
      return userRepositoryMock;
    }


    @Bean
    public RegExpLevelRepository regExpLevelRepository() {
      RegExpLevelRepository regExpLevelRepository = getRegExpLevelRepositoryMock();
      return regExpLevelRepository;
    }
  }
}
