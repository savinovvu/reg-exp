package ru.inbox.savinov_vu.app.tasks.level.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.app.tasks.level.repository.RegExpLevelRepository;
import ru.inbox.savinov_vu.app.users.repository.UserRepository;
import ru.inbox.savinov_vu.app.users.service.UserService;

import javax.annotation.Resource;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static ru.inbox.savinov_vu.test_helpers.mock.RegExpLevelMock.getRegExpLevelRepositoryMock;
import static ru.inbox.savinov_vu.test_helpers.mock.UserMock.getUserRepositoryMock;
import static ru.inbox.savinov_vu.test_helpers.mock.UserMock.getUserServiceMock;



@ExtendWith(SpringExtension.class)
class RegExpLevelServiceTest {


  @Resource
  private RegExpLevelService regExpLevelService;





  @Test
  public void findById() {
    RegExpLevel level = regExpLevelService.findById(1);
    assertEquals("object id must be 1", level.getId().intValue(), 1);
  }




  @TestConfiguration
  public static class RegExpLevelServiceTestContextConfiguration {


    @Bean
    public RegExpLevelService regExpLevelService() {
      return new RegExpLevelService(regExpLevelRepository(), userService());
    }


    @Bean
    public RegExpLevelRepository regExpLevelRepository() {
      RegExpLevelRepository regExpLevelRepositoryMock = getRegExpLevelRepositoryMock();
      return regExpLevelRepositoryMock;
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

  }


}
