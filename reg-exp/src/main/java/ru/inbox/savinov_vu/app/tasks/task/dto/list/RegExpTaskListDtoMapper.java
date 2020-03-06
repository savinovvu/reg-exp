package ru.inbox.savinov_vu.app.tasks.task.dto.list;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.common.interfaces.DtoEntityMapper;



@Mapper
public interface RegExpTaskListDtoMapper extends DtoEntityMapper<RegExpTaskListDto, RegExpTask> {

  @Override
  @Mapping(source = "enDescription", target = "description")
  @Mapping(source = "enTitle", target = "title")
  RegExpTaskListDto mapEntityToDto(RegExpTask entity);

  @Override
  RegExpTask mapDtoToEntity(RegExpTaskListDto dto);
}
