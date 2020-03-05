package ru.inbox.savinov_vu.app.tasks.task.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.app.tasks.task.service.RegExpTaskService;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;



@RestController
@AllArgsConstructor
public class RegExpTaskController {

  @Resource
  private final RegExpTaskService regExpTaskService;

  @Resource
  private final UserService userService;


  @GetMapping("/v1/tasks/regexptask/parent/{id}")
  public ResponseEntity<List<RegExpTask>> getAllByParentId(Principal principal, HttpServletRequest request, @PathVariable("id") Integer id) {
    User user = userService.getByLogin(principal.getName());
    return new ResponseEntity(regExpTaskService.getAllByParentId(id, user.getId()), HttpStatus.ACCEPTED.OK);
  }


  @GetMapping("/v1/tasks/regexptask/{id}")
  public ResponseEntity<RegExpTask> getById(@PathVariable("id") Integer id) {
    return new ResponseEntity(regExpTaskService.getById(id), HttpStatus.ACCEPTED.OK);
  }


  @GetMapping("/v1/tasks/regexptask/check/{id}")
  public ResponseEntity<List<RegExpTask>> getDisabledTask() {
    return new ResponseEntity(regExpTaskService.getDisabledTask(), HttpStatus.ACCEPTED.OK);
  }


  @GetMapping("/v1/tasks/regexptask/byNumber/{parentNumber}/{number}")
  public ResponseEntity getByParentNumberAndByNumber(@PathVariable("parentNumber") Integer parentNumber,
                                                     @PathVariable("number") Integer number) {
    return new ResponseEntity(regExpTaskService.getByParentNumberAndByNumber(parentNumber, number),
      HttpStatus.ACCEPTED.OK);
  }





  @PutMapping("/v1/tasks/regexptask")
  public ResponseEntity<RegExpTask> update(RegExpTask regExpTask) {
    return new ResponseEntity(regExpTaskService.update(regExpTask), HttpStatus.ACCEPTED.OK);
  }


  @PutMapping("/v1/tasks/regexptask/check/{id}")
  public ResponseEntity check(@PathVariable("id") Integer id,
                              @RequestBody String answer) {
    return new ResponseEntity(regExpTaskService.check(id, answer), HttpStatus.ACCEPTED.OK);
  }


  @DeleteMapping("/v1/tasks/regexptask/{id}")
  public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
    regExpTaskService.delete(id);
    return new ResponseEntity(Boolean.TRUE, HttpStatus.ACCEPTED.OK);
  }


}
