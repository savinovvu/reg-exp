package ru.inbox.savinov_vu.app.tasks.proposeTask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.app.checker.RegExpTaskCheckerService;
import ru.inbox.savinov_vu.app.checker.model.RegExpTaskResulter;
import ru.inbox.savinov_vu.app.checker.model.TaskCondition;
import ru.inbox.savinov_vu.app.tasks.proposeTask.dto.ProposeTaskRequestDto;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.app.tasks.task.service.RegExpTaskService;
import ru.inbox.savinov_vu.app.users.model.User;



@Service
@RequiredArgsConstructor
public class ProposeTaskService {

  private final RegExpTaskCheckerService regExpTaskCheckerService;

  private final RegExpTaskService regExpTaskService;


  public boolean save(ProposeTaskRequestDto requestDto, User author) {
    TaskCondition taskCondition = TaskCondition.of(requestDto);
    RegExpTaskResulter result = regExpTaskCheckerService.check(taskCondition);
    if (result.getSuccess()) {
      RegExpTask regExpTask = requestDto.toRegExpTask(author);
      regExpTaskService.add(regExpTask);
      return true;
    } else {
      return false;
    }

  }
}
