package ru.inbox.savinov_vu.app.checker;

import org.springframework.stereotype.Component;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;

import java.util.regex.Pattern;

import static java.util.Objects.isNull;
import static ru.inbox.savinov_vu.app.checker.WrongCheckStatus.*;
import static ru.inbox.savinov_vu.app.checker.WrongCheckStatus.UNMATCH;



@Component
public class RegExpTaskChecker {


  private boolean checkMatches(String researchedString, String regExp) {
    var pattern = Pattern.compile(regExp);
    var matcher = pattern.matcher(researchedString);
    return matcher.matches();
  }


  private boolean checkRequiredString(String requiredString, String regExp) {
    return regExp.contains(requiredString);
  }


  private boolean isEqualExcludedAnswer(String excludedAnswer, String regExp) {
    return regExp.equals(excludedAnswer);
  }


  public TaskResulter check(RegExpTask regExpTask, String answer) {

    var result = new TaskResulter();

    if (isNull(answer)) {
      return result.setWrong("not answer", NOT_ANSWER);
    }

    regExpTask.getMatchStrings().stream()
      .filter(v -> !checkMatches(v, answer))
      .forEach(v -> result.setWrong(v, UNMATCH));

    regExpTask.getExcludedStrings().stream()
      .filter(v -> checkMatches(v, answer))
      .forEach(v -> result.setWrong(v, MATCH));

    regExpTask.getRequiredSubStrings().stream()
      .filter(v -> !checkRequiredString(v, answer))
      .forEach(v -> result.setWrong(v, UNUSED));

    regExpTask.getExcludedAnswers().stream()
      .filter(v -> isEqualExcludedAnswer(v, answer))
      .forEach(v -> result.setWrong(v, EQUALS));

    return result;
  }
}
