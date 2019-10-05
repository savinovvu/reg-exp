package ru.inbox.savinov_vu.app.tasks.level.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.app.tasks.level.service.RegExpLevelService;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.service.UserService;
import ru.inbox.savinov_vu.core.interfaces.OperationResulter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;



@RestController
@AllArgsConstructor
public class RegExpLevelController {

  @Resource
  private final RegExpLevelService regExpLevelService;

  @Resource
  private final UserService userService;

  @GetMapping("/v1/tasks/regexplevel")
  public ResponseEntity<List<RegExpLevel>> getAll(HttpServletRequest request, Principal principal) {
    User user = userService.getByLogin(principal.getName());
    return new ResponseEntity(regExpLevelService.getAll(user.getId()), HttpStatus.ACCEPTED.OK);
  }


  @GetMapping("/v1/tasks/regexplevel/{id}")
  public ResponseEntity<RegExpLevel> getById(@PathVariable("id") Integer id) {
    return new ResponseEntity(regExpLevelService.getById(id), HttpStatus.ACCEPTED.OK);
  }


  @GetMapping("/v1/tasks/regexplevel/byNumber/{number}")
  public ResponseEntity<RegExpLevel> getByNumber(@PathVariable("number") Integer number) {
    return new ResponseEntity(regExpLevelService.getByNumber(number), HttpStatus.ACCEPTED.OK);
  }


  @PostMapping("/v1/tasks/regexplevel")
  public ResponseEntity<OperationResulter<String>> add(RegExpLevel regExpLevel) {
    return new ResponseEntity(regExpLevelService.add(regExpLevel), HttpStatus.ACCEPTED.OK);
  }


  @PutMapping("/v1/tasks/regexplevel")
  public ResponseEntity<RegExpLevel> update(RegExpLevel regExpLevel) {
    return new ResponseEntity(regExpLevelService.update(regExpLevel), HttpStatus.ACCEPTED.OK);
  }


  @DeleteMapping("/v1/tasks/regexplevel/{id}")
  public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
    regExpLevelService.delete(id);
    return new ResponseEntity(Boolean.TRUE, HttpStatus.ACCEPTED.OK);
  }


}
