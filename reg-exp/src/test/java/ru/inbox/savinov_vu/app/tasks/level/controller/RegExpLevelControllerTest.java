package ru.inbox.savinov_vu.app.tasks.level.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.app.tasks.level.repository.RegExpLevelRepository;
import ru.inbox.savinov_vu.app.tasks.level.service.RegExpLevelService;
import ru.inbox.savinov_vu.app.users.repository.UserRepository;
import ru.inbox.savinov_vu.app.users.service.UserService;
import ru.inbox.savinov_vu.config.AbstractControllerTest;
import ru.inbox.savinov_vu.test_helpers.utils.WebTestHelper;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static ru.inbox.savinov_vu.test_helpers.mock.RegExpLevelMock.getRegExpLevelRepositoryMock;
import static ru.inbox.savinov_vu.test_helpers.mock.RegExpLevelMock.getRegExpLevelServiceMock;
import static ru.inbox.savinov_vu.test_helpers.mock.UserMock.getUserRepositoryMock;
import static ru.inbox.savinov_vu.test_helpers.mock.UserMock.getUserServiceMock;



class RegExpLevelControllerTest extends AbstractControllerTest {

  @Resource
  private WebTestHelper webTestHelper;

  @Resource
  private RegExpLevelService regExpLevelService;


  @Test
  @Disabled
  void getAll() {
    String result = webTestHelper.performRequest(get("/v1/tasks/regexplevel"));
    List<RegExpLevel> all = regExpLevelService.findAll();
    String expected = webTestHelper.objectToJson(all);
    assertEquals("json must be right", expected, result);
  }


  @Test
  @Disabled
  void getById() {
    String result = webTestHelper.performRequest(get("/v1/tasks/regexplevel/1"));
    RegExpLevel regExpLevel = regExpLevelService.findById(1);
    String expected = webTestHelper.objectToJson(regExpLevel);
    assertEquals("json must be right", expected, result);
  }


  @TestConfiguration
  public static class RegExpLevelServiceTestContextConfiguration {


    @Bean
    public RegExpLevelController regExpLevelController() {
      return new RegExpLevelController(regExpLevelService());
    }


    @Bean
    public RegExpLevelService regExpLevelService() {
      RegExpLevelService regExpLevelServiceMock = getRegExpLevelServiceMock();
      return regExpLevelServiceMock;
    }


    @Bean
    public UserService userService() {
      UserService mock = getUserServiceMock();
      return mock;
    }


    @Bean
    public UserRepository userRepository() {
      UserRepository userRepositoryMock = getUserRepositoryMock();
      return userRepositoryMock;
    }


    @Bean
    public RegExpLevelRepository regExpLevelRepository() {
      RegExpLevelRepository regExpLevelRepository = getRegExpLevelRepositoryMock();
      return regExpLevelRepository;
    }
  }
}
