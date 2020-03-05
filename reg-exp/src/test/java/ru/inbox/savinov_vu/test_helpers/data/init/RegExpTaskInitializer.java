package ru.inbox.savinov_vu.test_helpers.data.init;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.app.tasks.task.repository.RegExpTaskRepository;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.repository.UserRepository;
import ru.inbox.savinov_vu.test_helpers.data.factories.regexpTask.WordRegExpTaskFactory;
import ru.inbox.savinov_vu.test_helpers.data.factories.user.UserFactory;

import javax.annotation.Resource;


@Service
@RequiredArgsConstructor
public class RegExpTaskInitializer {

  @Resource
  private final RegExpTaskRepository regExpTaskRepository;

  @Resource
  private final UserRepository userRepository;

  public RegExpTask initOne() {
    userRepository.deleteAll();
    regExpTaskRepository.deleteAll();
    User one = UserFactory.getOne();
    User user = userRepository.saveAndFlush(one);
    RegExpTask wordTask = WordRegExpTaskFactory.getWordTask();
    wordTask.setAuthor(user);
    RegExpTask regExpTask = regExpTaskRepository.saveAndFlush(wordTask);
    return regExpTask;

  }
}
