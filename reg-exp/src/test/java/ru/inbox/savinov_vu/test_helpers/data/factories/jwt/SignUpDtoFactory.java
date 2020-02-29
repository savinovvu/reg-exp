package ru.inbox.savinov_vu.test_helpers.data.factories.jwt;

import ru.inbox.savinov_vu.app.users.model.Sex;
import ru.inbox.savinov_vu.core.security.jwt.dto.SignUpDto;

import static ru.inbox.savinov_vu.test_helpers.data.factories.constant.Constants.LocalDateConstant.START_UNIX_EPOCH_LOCAL_DATE;



public class SignUpDtoFactory {

  public static SignUpDto of() {
    SignUpDto signUpDto = new SignUpDto()
      .setFirstName("signUpFirstName")
      .setLastName("signUpFirstName")
      .setLogin("login")
      .setEmail("email@email.com")
      .setSex(Sex.WOMAN)
      .setBirthDate(START_UNIX_EPOCH_LOCAL_DATE)
      .setPassword("password")
      .setRepeatPassword("password");
    return signUpDto;
  }


}
