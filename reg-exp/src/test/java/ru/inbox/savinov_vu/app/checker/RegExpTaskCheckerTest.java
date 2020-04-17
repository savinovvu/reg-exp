package ru.inbox.savinov_vu.app.checker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.inbox.savinov_vu.app.checker.model.RegExpTaskResulter;
import ru.inbox.savinov_vu.app.checker.model.TaskCondition;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.test_helpers.data.factories.regexpTask.DigitalRegExpTaskFactory;
import ru.inbox.savinov_vu.test_helpers.data.factories.regexpTask.WordRegExpTaskFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class RegExpTaskCheckerTest {

  private RegExpTaskChecker taskChecker;


  @BeforeEach
  void init() {
    taskChecker = new RegExpTaskChecker();
  }


  @ParameterizedTest
  @MethodSource("getValidTasks")
  public void check_valid(RegExpTask task, String message) {
    for (String v : task.getAnswers()) {
      RegExpTaskResulter check = taskChecker.check(TaskCondition.of(task, v));
      assertEquals(true, check.getSuccess(), message + ", answer: " + v);
    }
  }


  public static Stream<Arguments> getValidTasks() {
    return Stream.of(
      Arguments.of(WordRegExpTaskFactory.getWordTask(), "simple word"),

      Arguments.of(DigitalRegExpTaskFactory.getOneDecimalRange(), "one digit range [0-9]"),
      Arguments.of(DigitalRegExpTaskFactory.getThreeDecimalRange(), "three digit range [0-9]{3}"),
      Arguments.of(DigitalRegExpTaskFactory.getMultipleDecimalRange(), "multiple digit range [0-9]*"),

      Arguments.of(DigitalRegExpTaskFactory.getOneBinary(), "one digit range [0,1]"),
      Arguments.of(DigitalRegExpTaskFactory.getThreeBinary(), "three digits range [0,1]{3}"),
      Arguments.of(DigitalRegExpTaskFactory.getMultipleBinary(), "multiple digits range [0,1]*"),

      Arguments.of(DigitalRegExpTaskFactory.getOneRangeWithPoint(), "one digit range [0-3,5]"),
      Arguments.of(DigitalRegExpTaskFactory.getThreeRangeWithPoint(), "three digits range [0-3,5]"),
      Arguments.of(DigitalRegExpTaskFactory.getMultipleRangeWithPoint(), "multiple digits range [0-3,5]")
    );
  }


  @Test
  public void digitalTask_invalid_excluded_answer() {
    RegExpTask task = DigitalRegExpTaskFactory.getOneDecimalRange();
    RegExpTaskResulter check = taskChecker.check(TaskCondition.of(task, task.getExcludedAnswers().get(0)));
    assertEquals(false, check.getSuccess());
  }

}
