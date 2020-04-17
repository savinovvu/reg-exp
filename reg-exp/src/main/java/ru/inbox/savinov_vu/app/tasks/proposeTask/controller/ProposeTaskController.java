package ru.inbox.savinov_vu.app.tasks.proposeTask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.inbox.savinov_vu.app.checker.RegExpTaskChecker;
import ru.inbox.savinov_vu.app.checker.model.RegExpTaskResulter;
import ru.inbox.savinov_vu.app.checker.model.TaskCondition;
import ru.inbox.savinov_vu.app.tasks.level.dto.RegExpLevelDto;
import ru.inbox.savinov_vu.app.tasks.proposeTask.dto.ProposeTaskRequestDto;
import ru.inbox.savinov_vu.app.tasks.proposeTask.service.ProposeTaskService;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.core.security.service.SecurityService;

import java.security.Principal;
import java.util.List;



@Controller
@RequiredArgsConstructor
public class ProposeTaskController {

  private final RegExpTaskChecker regExpTaskChecker;

  private final ProposeTaskService proposeTaskService;

  private final SecurityService securityService;

  @PostMapping("/v1/proposeTask/check")
  public ResponseEntity<List<RegExpLevelDto>> check(@RequestBody ProposeTaskRequestDto requestDto) {
    TaskCondition taskCondition = TaskCondition.of(requestDto);
    RegExpTaskResulter result = regExpTaskChecker.check(taskCondition);
    return new ResponseEntity(result, HttpStatus.OK);
  }


  @PostMapping("/v1/proposeTask/propose")
  public ResponseEntity propose(@RequestBody ProposeTaskRequestDto requestDto, Principal principal) {
    User user = securityService.getUserByPrincipal(principal);
    boolean isSuccess = proposeTaskService.save(requestDto, user);
    if (!isSuccess) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok().build();
  }


}
