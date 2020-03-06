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
import ru.inbox.savinov_vu.app.checker.model.TaskResulter;
import ru.inbox.savinov_vu.app.tasks.task.dto.detail.RegExpTaskDetailDto;
import ru.inbox.savinov_vu.app.tasks.task.dto.detail.RegExpTaskDetailDtoMapper;
import ru.inbox.savinov_vu.app.tasks.task.dto.list.RegExpTaskListDto;
import ru.inbox.savinov_vu.app.tasks.task.dto.list.RegExpTaskListDtoMapper;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.app.tasks.task.service.RegExpTaskService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;



@RestController
@AllArgsConstructor
public class RegExpTaskController {

  @Resource
  private final RegExpTaskService regExpTaskService;


  @PutMapping("/v1/tasks/regexptask/check/{id}")
  public ResponseEntity check(@PathVariable("id") Integer id,
                              @RequestBody String answer) {
    TaskResulter check = regExpTaskService.check(id, answer);
    return new ResponseEntity(check, HttpStatus.OK);
  }


  @PutMapping("/v1/tasks/regexptask/registerAnswer/{id}")
  public ResponseEntity register(@PathVariable("id") Integer id,
                                 @RequestBody String answer,
                                 HttpServletRequest httpServletRequest) {
    Integer userId = Integer.valueOf(httpServletRequest.getHeader("id"));
    TaskResulter check = regExpTaskService.register(id, answer, userId);
    return new ResponseEntity(check, HttpStatus.OK);
  }


  @GetMapping("/v1/tasks/regexptask/byLevel/{levelNumber}")
  public ResponseEntity byLevelNumberAndTaskNumber(@PathVariable Integer levelNumber) {

    List<RegExpTask> regExpTasks = regExpTaskService.getbyLevelNumber(levelNumber);
    List<RegExpTaskListDto> regExpTaskListDtos = Mappers.getMapper(RegExpTaskListDtoMapper.class).mapEntityToDto(regExpTasks);
    return new ResponseEntity(regExpTaskListDtos, HttpStatus.OK);
  }


  @GetMapping("/v1/tasks/regexptask/byLevel/{levelNumber}/byNumber/{taskNumber}")
  public ResponseEntity byLevelNumberAndTaskNumber(@PathVariable Integer levelNumber,
                                                   @PathVariable Integer taskNumber) {
    RegExpTask regExpTask = regExpTaskService.getTaskByLevelNumberAndByNumber(levelNumber, taskNumber);
    RegExpTaskDetailDto regExpTaskDetailDto = Mappers.getMapper(RegExpTaskDetailDtoMapper.class).mapEntityToDto(regExpTask);
    return new ResponseEntity(regExpTaskDetailDto, HttpStatus.OK);
  }


}
