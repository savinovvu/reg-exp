package ru.inbox.savinov_vu.app.tasks.task.service;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.inbox.savinov_vu.app.checker.RegExpTaskChecker;
import ru.inbox.savinov_vu.app.checker.model.RegExpTaskResulter;
import ru.inbox.savinov_vu.app.checker.model.TaskCondition;
import ru.inbox.savinov_vu.app.tasks.task.dto.list.RegExpTaskListDto;
import ru.inbox.savinov_vu.app.tasks.task.dto.list.RegExpTaskListDtoMapper;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.app.tasks.task.repository.RegExpTaskRepository;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.service.UserService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;



@Service
@RequiredArgsConstructor
public class RegExpTaskService {

  @Resource
  private final RegExpTaskRepository repository;

  @Resource
  private final RegExpTaskChecker regExpTaskChecker;

  @Resource
  private final UserService userService;


  @Transactional(readOnly = true)
  public List<RegExpTaskListDto> getbyLevelNumber(Integer regExpLevelId, Integer userId) {
    List<RegExpTask> all = repository.findByRegExpLevelNumberOrderByNumber(regExpLevelId);
    User user = userService.getById(userId);
    List<RegExpTaskListDto> result = all.stream().map(v -> Mappers.getMapper(RegExpTaskListDtoMapper.class)
      .mapEntityToDto(v)
      .setSolve(v.getUsers().contains(user)))
      .collect(Collectors.toList());

    return result;
  }


  @Transactional(readOnly = true)
  public RegExpTask getById(Integer id) {
    return repository.findById(id).get();
  }


  @Transactional(readOnly = true)
  public RegExpTask getTaskByLevelNumberAndByNumber(Integer levelNumber, Integer taskNumber) {
    return repository.findTaskByLevelNumberAndByNumber(levelNumber, taskNumber);
  }


  public RegExpTaskResulter check(Integer id, String answer) {
    var checkedTask = getById(id);
    TaskCondition taskCondition = TaskCondition.of(checkedTask, answer);
    return regExpTaskChecker.check(taskCondition);
  }


  @Transactional
  public RegExpTaskResulter register(Integer taskId, String answer, Integer userId) {
    var checkedTask = getById(taskId);
    TaskCondition taskCondition = TaskCondition.of(checkedTask, answer);
    RegExpTaskResulter check = regExpTaskChecker.check(taskCondition);
    if (!check.getSuccess()) {
      return check;
    }
    User user = userService.getById(userId);
    user.solveTask(checkedTask);
    return check;
  }


  @Transactional
  public RegExpTask add(RegExpTask regExpTask) {
    if (nonNull(regExpTask.getId())) {
      return regExpTask;
    }
    RegExpTask result = repository.save(regExpTask);
    return result;
  }


}
