package ru.inbox.savinov_vu.test_helpers.data.factories.user;

import org.mapstruct.factory.Mappers;
import ru.inbox.savinov_vu.app.users.dto.UserDto;
import ru.inbox.savinov_vu.app.users.dto.UserDtoMapper;
import ru.inbox.savinov_vu.app.users.model.User;



public class UserDtoFactory {

  public static UserDto of() {
    User one = UserFactory.getOne();
    UserDto userDto = Mappers.getMapper(UserDtoMapper.class).mapEntityToDto(one);
    return userDto;
  }
}
