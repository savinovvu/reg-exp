package ru.inbox.savinov_vu.app.tasks.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.inbox.savinov_vu.app.checker.RegExpTaskChecker;
import ru.inbox.savinov_vu.app.checker.TaskResulter;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.app.tasks.task.repository.RegExpTaskRepository;
import ru.inbox.savinov_vu.app.users.repository.UserRepository;
import ru.inbox.savinov_vu.core.interfaces.OperationResulter;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;



@Service
@RequiredArgsConstructor
public class RegExpTaskService {

  @Resource
  private final RegExpTaskRepository repository;

  @Resource
  private final UserRepository userRepository;

  @Resource
  private final RegExpTaskChecker regExpTaskChecker;


  @Transactional(readOnly = true)
  public List<RegExpTask> getAll() {
    return repository.findAll();
  }


  @Transactional(readOnly = true)
  public List<RegExpTask> getAllByParentId(Integer id, Integer userId) {
    List<RegExpTask> all = repository.getByRegExpLevelIdAndNumberIsNotNullOrderByNumber(id);
    Set<RegExpTask> solvedLevels = userRepository.findSolvedTasks(userId);
    for (RegExpTask regExpTask : all) {
      if (solvedLevels.contains(regExpTask)) {
        regExpTask.setSolve(true);
      } else {
        regExpTask.setSolve(false);
      }
    }
    return all;
  }


  @Transactional(readOnly = true)
  public RegExpTask getById(Integer id) {
    return repository.findById(id).get();
  }


  @Transactional(readOnly = true)
  public List<RegExpTask> getDisabledTask() {
    return repository.getByEnabledOrderById(false);
  }


  @Transactional(readOnly = true)
  public RegExpTask getByParentNumberAndByNumber(Integer parentNumber, Integer number) {
    return repository.getTaskByLevelIdAndByNumber(parentNumber, number);
  }


  @Transactional
  public OperationResulter add(RegExpTask regExpTask) {

    String answer = regExpTask.getAnswers().stream()
      .findFirst()
      .orElse(null);

    TaskResulter check = regExpTaskChecker.check(regExpTask, answer);
    if (check.getSuccess()) {
      repository.saveAndFlush(regExpTask);
    }
    return check;
  }


  @Transactional
  public RegExpTask update(RegExpTask regExpTask) {
    return repository.saveAndFlush(regExpTask);
  }


  @Transactional
  public boolean delete(Integer id) {
    repository.deleteById(id);
    return true;
  }


  public TaskResulter check(Integer id, String answer) {
    var checkedTask = getById(id);
    return regExpTaskChecker.check(checkedTask, answer);
  }


}
