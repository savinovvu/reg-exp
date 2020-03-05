package ru.inbox.savinov_vu.app.users.mapper;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.inbox.savinov_vu.app.users.dto.UserDto;
import ru.inbox.savinov_vu.app.users.dto.UserDtoMapper;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.service.UserService;
import ru.inbox.savinov_vu.common.interfaces.DtoEntityMapper;

import javax.annotation.Resource;



@Component
@RequiredArgsConstructor
public class UserMapper implements DtoEntityMapper<UserDto, User> {

  @Resource
  private final UserService userService;


  @Override
  public UserDto mapEntityToDto(User entity) {
    return Mappers.getMapper(UserDtoMapper.class).mapEntityToDto(entity);
  }


  @Override
  public User mapDtoToEntity(UserDto dto) {
    User byId = userService.getById(Integer.valueOf(dto.getId()));
    User user = dto.updateFromExist(byId);
    return user;
  }
}
