package ru.inbox.savinov_vu.app.tasks.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.inbox.savinov_vu.app.checker.RegExpTaskChecker;
import ru.inbox.savinov_vu.app.checker.model.TaskResulter;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.app.tasks.task.repository.RegExpTaskRepository;

import javax.annotation.Resource;
import java.util.List;



@Service
@RequiredArgsConstructor
public class RegExpTaskService {

  @Resource
  private final RegExpTaskRepository repository;

  @Resource
  private final RegExpTaskChecker regExpTaskChecker;


  @Transactional(readOnly = true)
  public List<RegExpTask> getbyLevelNumber(Integer regExpLevelId) {
    List<RegExpTask> all = repository.findByRegExpLevelNumberOrderByNumber(regExpLevelId);
    return all;
  }


  @Transactional(readOnly = true)
  public RegExpTask getById(Integer id) {
    return repository.findById(id).get();
  }


  @Transactional(readOnly = true)
  public RegExpTask getTaskByLevelNumberAndByNumber(Integer levelNumber, Integer taskNumber) {
    return repository.findTaskByLevelNumberAndByNumber(levelNumber, taskNumber);
  }


  public TaskResulter check(Integer id, String answer) {
    var checkedTask = getById(id);
    return regExpTaskChecker.check(checkedTask, answer);
  }


}
