package ru.inbox.savinov_vu.core.security.jwt.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import ru.inbox.savinov_vu.core.security.jwt.model.SecurityUser;



@Mapper
public interface LoginDtoMapper {

  LoginDto entityToDto(SecurityUser user);

  @Mappings({
    @Mapping(target = "id", ignore = true)
  })
  void fillEntity(LoginDto loginDto, @MappingTarget SecurityUser user);
}
