package ru.inbox.savinov_vu.app.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.model.UserDto;
import ru.inbox.savinov_vu.app.users.service.UserService;

import javax.annotation.Resource;
import java.util.List;

import static java.util.Objects.isNull;



@Controller
@RequiredArgsConstructor
public class UserController {


  @Resource
  private final UserService userService;

  @Resource
  private final PasswordEncoder passwordEncoder;


  @GetMapping("/v1/users/user")
  public ResponseEntity<List<User>> getAll() {
    return new ResponseEntity(userService.getAll(), HttpStatus.ACCEPTED.OK);
  }


  @GetMapping("/v1/users/user/{id}")
  public ResponseEntity<User> getById(@PathVariable("id") Integer id) {
    return new ResponseEntity(userService.getById(id), HttpStatus.ACCEPTED.OK);
  }


  @PostMapping("/page/users/user")
  public String add(UserDto userDto) {
    User user = UserDto.dtoToEntity(userDto);
    if (isNull(user)) {
      return "redirect:/page/sign-up";
    }
    String encode = passwordEncoder.encode(user.getPassword());
    user.setPassword(encode);
    userService.add(user);
    return "redirect:/page/login";
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
