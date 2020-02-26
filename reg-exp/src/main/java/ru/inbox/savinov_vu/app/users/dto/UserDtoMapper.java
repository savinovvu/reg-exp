package ru.inbox.savinov_vu.app.users.dto;

import org.mapstruct.Mapper;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.common.interfaces.DtoEntityMapper;



@Mapper(componentModel = "spring")
public interface UserDtoMapper extends DtoEntityMapper<UserDto, User> {

  @Override
  UserDto mapEntityToDto(User entity);

  @Override
  User mapDtoToEntity(UserDto dtoList);
}
