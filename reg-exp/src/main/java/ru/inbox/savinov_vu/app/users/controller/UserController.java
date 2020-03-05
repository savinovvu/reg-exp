package ru.inbox.savinov_vu.app.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.inbox.savinov_vu.app.users.dto.UserDto;
import ru.inbox.savinov_vu.app.users.dto.UserFilterDto;
import ru.inbox.savinov_vu.app.users.mapper.UserMapper;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.repository.UserFilter;
import ru.inbox.savinov_vu.app.users.service.UserService;
import ru.inbox.savinov_vu.common.paged.PagedResultList;

import javax.annotation.Resource;
import javax.validation.Valid;



@Controller
@RequiredArgsConstructor
public class UserController {


  @Resource
  private final UserService userService;

  @Resource
  private final UserMapper userMapper;


  @GetMapping("/v1/users/user/filter")
  public ResponseEntity<PagedResultList<UserDto>> getWithFilter(@Valid UserFilterDto filterDto) {
    UserFilter filter = UserFilter.of(filterDto);
    PagedResultList<UserDto> result = userService.getByFilter(filter);
    return ResponseEntity.ok(result);
  }


  @PutMapping("/v1/users/user")
  public ResponseEntity update(@Valid @RequestBody UserDto userDto) {
    User user = userMapper.mapDtoToEntity(userDto);
    userService.update(user);
    return ResponseEntity.ok(true);
  }


  @DeleteMapping("/v1/users/user/{id}")
  public ResponseEntity delete(@Valid @PathVariable Integer id) {
    userService.delete(id);
    return ResponseEntity.ok(true);
  }

}
