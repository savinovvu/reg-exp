package ru.inbox.savinov_vu.app.tasks.task.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.common.interfaces.DtoEntityMapper;



@Mapper
public interface RegExpTaskDtoMapper extends DtoEntityMapper<RegExpTaskDto, RegExpTask> {

  @Override
  @Mapping(source = "enDescription", target = "description")
  @Mapping(source = "enTitle", target = "title")
  RegExpTaskDto mapEntityToDto(RegExpTask entity);

  @Override
  RegExpTask mapDtoToEntity(RegExpTaskDto dto);
}
