package ru.inbox.savinov_vu.app.tasks.level.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.common.interfaces.DtoEntityMapper;



@Mapper
public interface RegExpLevelDtoMapper extends DtoEntityMapper<RegExpLevelDto, RegExpLevel> {

  @Override
  @Mapping(source = "enDescription", target = "description")
  RegExpLevelDto mapEntityToDto(RegExpLevel entity);

  @Override
  RegExpLevel mapDtoToEntity(RegExpLevelDto dto);
}
