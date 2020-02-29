package ru.inbox.savinov_vu.app.checker;

import org.junit.jupiter.api.Test;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static ru.inbox.savinov_vu.test_helpers.data.factories.RegExpTaskFactory.getRegExpTaskWithMistakes;



public class RegExpTaskCheckerTest {

  @Test
  public void missAnswerTest() {
    RegExpTask regExpTask = getRegExpTaskWithMistakes();
    RegExpTaskChecker regExpTaskChecker = new RegExpTaskChecker();
    TaskResulter resulter = regExpTaskChecker.check(regExpTask, "sdfh");
    assertEquals("result must be false", resulter.getSuccess(), false);
    assertEquals("Unmatch result with 3 mistakes", resulter.getResult().get(WrongCheckStatus.UNMATCH).size(), 3);
    assertEquals("Unused result with 1 mistakes", resulter.getResult().get(WrongCheckStatus.UNUSED).size(), 1);
  }


  @Test
  public void successAnswerTest() {
    RegExpTask regExpTask = new RegExpTask()
      .setExcludedStrings(List.of())
      .setExcludedAnswers(List.of())
      .setMatchedStrings(List.of("a"))
      .setRequiredSubStrings(List.of("a"));
    RegExpTaskChecker regExpTaskChecker = new RegExpTaskChecker();
    TaskResulter resulter = regExpTaskChecker.check(regExpTask, "a");
    assertEquals("result must be false", resulter.getSuccess(), true);
  }

}
