package ru.inbox.savinov_vu.app.tasks.task.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.app.tasks.level.repository.RegExpLevelRepository;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.repository.UserRepository;
import ru.inbox.savinov_vu.config.AbstractSpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static ru.inbox.savinov_vu.test_helpers.data.factories.RegExpLevelFactory.getRegExpLevel;
import static ru.inbox.savinov_vu.test_helpers.data.factories.regexpTask.RegExpTaskFactory.getRegExpTask;
import static ru.inbox.savinov_vu.test_helpers.data.factories.regexpTask.RegExpTaskFactory.getRegExpTaskWithNumber;
import static ru.inbox.savinov_vu.test_helpers.data.factories.user.UserFactory.getOne;



class RegExpTaskRepositoryTest extends AbstractSpringBootTest {

  @Resource
  private RegExpTaskRepository repository;

  @Resource
  private UserRepository userRepository;

  @Resource
  private RegExpLevelRepository regExpLevelRepository;

  private static User author;

  private static RegExpLevel level;

  private static RegExpTask task;


  @BeforeEach
  void initTest() {
    repository.deleteAll();
    userRepository.deleteAll();
    regExpLevelRepository.deleteAll();

    RegExpTask regExpTask = getRegExpTask();
    RegExpLevel regExpLevel = getRegExpLevel();
    level = regExpLevelRepository.saveAndFlush(regExpLevel);
    User user = getOne();
    author = userRepository.saveAndFlush(user);
    regExpTask.setAuthor(author);
    regExpTask.setRegExpLevel(level);
    task = repository.saveAndFlush(regExpTask);
  }


  @Test
  void createAndReadLevelTest() {
    RegExpTask result = repository.findById(task.getId()).orElse(null);
    assertEquals("RegExpTask must have id", result.getId().intValue(), task.getId());
  }


  @Test
  void updateLevelTest() {
    RegExpTask finded = repository.findById(task.getId()).orElse(null);
    finded.setNumber(5);
    RegExpTask result = repository.saveAndFlush(finded);
    assertEquals("RegExpLevel must have id", result.getNumber().intValue(), 5);
  }


  @Test
  void deleteLevelTest() {
    RegExpTask mockEntity = getRegExpTask();
    mockEntity.setAuthor(author);
    RegExpTask result = repository.saveAndFlush(mockEntity);
    repository.delete(result);
    RegExpTask nullLevel = repository.findById(result.getId()).orElse(null);
    assertNull(nullLevel, "RegExpLevel must be null");
  }


  @Test
  void getTaskByLevelIdAndByNumber() {
    RegExpTask mockEntity = getRegExpTaskWithNumber(2);
    mockEntity.setRegExpLevel(level);
    mockEntity.setAuthor(author);
    RegExpTask savedLevel = repository.saveAndFlush(mockEntity);
    RegExpTask byNumberLevel = repository.findTaskByLevelNumberAndByNumber(level.getNumber(), 2);
    assertEquals("RegExpLevel find by number not correct", byNumberLevel.getId(), savedLevel.getId());
  }





  @Test
  void getByEnabledOrderById() {
    repository.deleteAll();

    List<RegExpTask> regExpTaskWithNumber = List.of(
      getRegExpTaskWithNumber(1).setEnabled(true),
      getRegExpTaskWithNumber(2).setEnabled(false),
      getRegExpTaskWithNumber(3).setEnabled(true),
      getRegExpTaskWithNumber(4).setEnabled(false),
      getRegExpTaskWithNumber(5).setEnabled(true),
      getRegExpTaskWithNumber(6).setEnabled(false),
      getRegExpTaskWithNumber(7).setEnabled(true));
    regExpTaskWithNumber.forEach(v -> v.setAuthor(author));
    regExpTaskWithNumber.forEach(v -> v.setRegExpLevel(level));
    repository.saveAll(regExpTaskWithNumber);
    repository.flush();

    List<RegExpTask> enabledTasks = repository.getByEnabledOrderById(true);
    List<RegExpTask> disabledTask = repository.getByEnabledOrderById(false);


    assertEquals("Enabled list must contains 4 element", 4, enabledTasks.size());
    assertEquals("Disabled list must contains 3 element", 3, disabledTask.size());


    List<Integer> enabledTasksNumbers = enabledTasks.stream().map(v -> v.getNumber()).collect(Collectors.toList());
    List<Integer> disabledTasksNumbers = disabledTask.stream().map(v -> v.getNumber()).collect(Collectors.toList());
    List<Integer> expectedEnabled = List.of(1, 3, 5, 7);
    List<Integer> expectedDisabled = List.of(2, 4, 6);

    assertEquals("Elements must be ordered by number", expectedEnabled, enabledTasksNumbers);
    assertEquals("Elements must be ordered by number", expectedDisabled, disabledTasksNumbers);
  }


}
