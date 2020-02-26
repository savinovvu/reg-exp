package ru.inbox.savinov_vu.app.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import ru.inbox.savinov_vu.app.users.dto.UserDto;
import ru.inbox.savinov_vu.app.users.dto.UserFilterDto;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.repository.UserFilter;
import ru.inbox.savinov_vu.app.users.service.UserService;
import ru.inbox.savinov_vu.common.paged.PagedResultList;

import javax.annotation.Resource;



@Controller
@RequiredArgsConstructor
public class UserController {


  @Resource
  private final UserService userService;


  @GetMapping("/v1/users/user/filter")
  public ResponseEntity<PagedResultList<UserDto>> getWithFilter(UserFilterDto filterDto) {
    UserFilter filter = UserFilter.of(filterDto);
    PagedResultList<UserDto> result = userService.getByFilter(filter);
    return ResponseEntity.ok(result);
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
