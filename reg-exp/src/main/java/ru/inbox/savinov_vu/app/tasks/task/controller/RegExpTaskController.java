package ru.inbox.savinov_vu.app.tasks.task.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.app.tasks.task.service.RegExpTaskService;

import javax.annotation.Resource;



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


}
