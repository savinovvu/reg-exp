package ru.inbox.savinov_vu.app.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.service.UserService;
import ru.inbox.savinov_vu.core.interfaces.OperationResulter;

import javax.annotation.Resource;
import java.util.List;



@RestController
@RequiredArgsConstructor
public class UserController {


  @Resource
  private final UserService userService;


  @GetMapping("/v1/users/user")
  public ResponseEntity<List<User>> getAll() {
    return new ResponseEntity(userService.getAll(), HttpStatus.ACCEPTED.OK);
  }


  @GetMapping("/v1/users/user/{id}")
  public ResponseEntity<User> getById(@PathVariable("id") Integer id) {
    return new ResponseEntity(userService.getById(id), HttpStatus.ACCEPTED.OK);
  }


  @PostMapping("/v1/users/user")
  public ResponseEntity<OperationResulter<String>> add(User user) {
    return new ResponseEntity(userService.add(user), HttpStatus.ACCEPTED.OK);
  }


  @PutMapping("/v1/users/user")
  public ResponseEntity<User> update(User user) {
    return new ResponseEntity(userService.update(user), HttpStatus.ACCEPTED.OK);
  }


  @DeleteMapping("/v1/users/user/{id}")
  public ResponseEntity delete(@PathVariable("id") Integer id) {
    userService.delete(id);
    return new ResponseEntity(Boolean.TRUE, HttpStatus.ACCEPTED.OK);
  }


}
