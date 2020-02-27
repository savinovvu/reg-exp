package ru.inbox.savinov_vu.app.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.inbox.savinov_vu.app.users.dto.UserDto;
import ru.inbox.savinov_vu.app.users.dto.UserFilterDto;
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


  @GetMapping("/v1/users/user/filter")
  public ResponseEntity<PagedResultList<UserDto>> getWithFilter(@Valid UserFilterDto filterDto) {
    UserFilter filter = UserFilter.of(filterDto);
    PagedResultList<UserDto> result = userService.getByFilter(filter);
    return ResponseEntity.ok(result);
  }



}
