package ru.inbox.savinov_vu.app.checker;

import org.springframework.stereotype.Component;
import ru.inbox.savinov_vu.app.checker.model.ConditionResult;
import ru.inbox.savinov_vu.app.checker.model.TaskResulter;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;



@Component
public class RegExpTaskChecker {


  private boolean checkMatches(String researchedString, String regExp) {
    try {
      var pattern = Pattern.compile(regExp);
      var matcher = pattern.matcher(researchedString);
      return matcher.matches();
    } catch (PatternSyntaxException e) {
      return false;
    }
  }


  private boolean checkRequiredString(String requiredString, String regExp) {
    return regExp.contains(requiredString);
  }


  private boolean isEqualExcludedAnswer(String excludedAnswer, String regExp) {
    return regExp.equals(excludedAnswer);
  }


  public TaskResulter check(RegExpTask regExpTask, String answer) {

    var result = new TaskResulter();

    checkMatchedString(regExpTask, answer, result);
    checkExcludeString(regExpTask, answer, result);
    checkRequireSubstringinAnswer(regExpTask, answer, result);
    checkExcludedAnswer(regExpTask, answer, result);
    checkSpecialConditions(regExpTask, answer, result);
    return result;
  }


  private void checkSpecialConditions(RegExpTask regExpTask, String answer, TaskResulter result) {
    Integer maxLength = regExpTask.getMaxAnswerLength();
    if (nonNull(maxLength)) {
      result.getSpecialConditions().add(
        ConditionResult.of("Max is " + maxLength, answer.length() <= maxLength));
    }

    Integer minLength = regExpTask.getMinAnswerLength();
    if (nonNull(minLength)) {
      result.getSpecialConditions().add(
        ConditionResult.of("Min is " + minLength, answer.length() >= minLength));
    }

  }


  private void checkMatchedString(RegExpTask regExpTask, String answer, TaskResulter result) {
    List<ConditionResult> matchedResult = regExpTask.getMatchedStrings().stream()
      .map(v -> ConditionResult.of(v, checkMatches(v, answer)))
      .collect(Collectors.toList());
    result.setMatchedStrings(matchedResult);
  }


  private void checkExcludeString(RegExpTask regExpTask, String answer, TaskResulter result) {
    List<ConditionResult> excludedStringsResult = regExpTask.getExcludedStrings().stream()
      .map(v -> ConditionResult.of(v, !checkMatches(v, answer)))
      .collect(Collectors.toList());
    result.setExcludedStrings(excludedStringsResult);
  }


  private void checkRequireSubstringinAnswer(RegExpTask regExpTask, String answer, TaskResulter result) {
    List<ConditionResult> requireSubstringResult = regExpTask.getRequiredSubStrings().stream()
      .map(v -> ConditionResult.of(v, checkRequiredString(v, answer)))
      .collect(Collectors.toList());
    result.setRequiredSubStrings(requireSubstringResult);
  }


  private void checkExcludedAnswer(RegExpTask regExpTask, String answer, TaskResulter result) {
    List<ConditionResult> excludeAnswerResult = regExpTask.getExcludedAnswers().stream()
      .map(v -> ConditionResult.of(v, !isEqualExcludedAnswer(v, answer)))
      .collect(Collectors.toList());
    result.setExcludedAnswers(excludeAnswerResult);
  }


}
