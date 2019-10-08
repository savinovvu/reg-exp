package ru.inbox.savinov_vu.app.checker;

import org.junit.jupiter.api.Test;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.data.DataClassesFactory;



public class RegExpTaskCheckerTest {

  @Test
  public void missAnswerTest() {
    RegExpTask regExpTask = DataClassesFactory.getRegExpTask();
    RegExpTaskChecker regExpTaskChecker = new RegExpTaskChecker();
    TaskResulter sdfh = regExpTaskChecker.check(regExpTask, "sdfh");

  }

}
