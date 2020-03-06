package ru.inbox.savinov_vu.app.tasks.task.dto.detail;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.common.interfaces.DtoEntityMapper;



@Mapper
public interface RegExpTaskDetailDtoMapper extends DtoEntityMapper<RegExpTaskDetailDto, RegExpTask> {

  @Override
  @Mapping(source = "enDescription", target = "description")
  @Mapping(source = "enTitle", target = "title")
  RegExpTaskDetailDto mapEntityToDto(RegExpTask entity);

  @Override
  RegExpTask mapDtoToEntity(RegExpTaskDetailDto dto);
}
