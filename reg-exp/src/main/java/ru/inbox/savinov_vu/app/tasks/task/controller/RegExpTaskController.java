package ru.inbox.savinov_vu.app.tasks.task.controller;

import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.app.tasks.task.dto.RegExpTaskDto;
import ru.inbox.savinov_vu.app.tasks.task.dto.RegExpTaskDtoMapper;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.app.tasks.task.service.RegExpTaskService;

import javax.annotation.Resource;
import java.util.List;



@RestController
@AllArgsConstructor
public class RegExpTaskController {

  @Resource
  private final RegExpTaskService regExpTaskService;


  @PutMapping("/v1/tasks/regexptask/check/{id}")
  public ResponseEntity check(@PathVariable("id") Integer id,
                              @RequestBody String answer) {
    return new ResponseEntity(regExpTaskService.check(id, answer), HttpStatus.OK);
  }


  @GetMapping("/v1/tasks/regexptask/byLevel/{levelNumber}")
  public ResponseEntity byFilter(@PathVariable Integer levelNumber) {

    List<RegExpTask> regExpTasks = regExpTaskService.getbyLevelNumber(levelNumber);
    List<RegExpTaskDto> regExpTaskDtos = Mappers.getMapper(RegExpTaskDtoMapper.class).mapEntityToDto(regExpTasks);
    return new ResponseEntity(regExpTaskDtos, HttpStatus.OK);
  }

}
