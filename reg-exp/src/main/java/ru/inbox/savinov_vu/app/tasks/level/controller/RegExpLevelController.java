package ru.inbox.savinov_vu.app.tasks.level.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.app.tasks.level.service.RegExpLevelService;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;



@RestController
@AllArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RegExpLevelController {

  @Resource
  private final RegExpLevelService regExpLevelService;

  @Resource
  private final UserService userService;


  @GetMapping("/v1/tasks/regexplevel")
  public ResponseEntity<List<RegExpLevel>> getAll(HttpServletRequest request, Principal principal) {
    User user = userService.getByLogin(principal.getName());
    return new ResponseEntity(regExpLevelService.findAll(user.getId()), HttpStatus.OK);
  }


  @GetMapping("/v1/tasks/regexplevel/{id}")
  public ResponseEntity<RegExpLevel> getById(@PathVariable("id") Integer id) {
    return new ResponseEntity(regExpLevelService.findById(id), HttpStatus.OK);
  }


  @GetMapping("/v1/tasks/regexplevel/byNumber/{number}")
  public ResponseEntity<RegExpLevel> getByNumber(@PathVariable("number") Integer number) {
    return new ResponseEntity(regExpLevelService.findByNumber(number), HttpStatus.OK);
  }


  @PostMapping(value = "/v1/tasks/regexplevel")
  public ResponseEntity<RegExpLevel> create(@RequestBody RegExpLevel regExpLevel) {
    RegExpLevel result = regExpLevelService.create(regExpLevel);
    return new ResponseEntity(result, HttpStatus.OK);
  }


  @PutMapping("/v1/tasks/regexplevel")
  public ResponseEntity<RegExpLevel> update(@RequestBody RegExpLevel regExpLevel) {
    RegExpLevel result = regExpLevelService.update(regExpLevel);
    return new ResponseEntity(result, HttpStatus.OK);
  }


  @DeleteMapping("/v1/tasks/regexplevel/{id}")
  public ResponseEntity<Boolean> remove(@PathVariable("id") Integer id) {
    regExpLevelService.delete(id);
    return new ResponseEntity(Boolean.TRUE, HttpStatus.OK);
  }


}
