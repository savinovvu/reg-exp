package ru.inbox.savinov_vu.test_helpers.data.factories.jwt;

import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.core.security.jwt.dto.LoginDto;
import ru.inbox.savinov_vu.test_helpers.data.factories.user.UserFactory;



public class LoginDtoFactory {

  public static LoginDto getOne() {
    User one = UserFactory.getOne();
    LoginDto loginDto = new LoginDto(one.getLogin(), one.getPassword());
    return loginDto;
  }


  public static LoginDto getFromUser(User user) {
    LoginDto loginDto = new LoginDto(user.getLogin(), user.getPassword());
    return loginDto;
  }

}
