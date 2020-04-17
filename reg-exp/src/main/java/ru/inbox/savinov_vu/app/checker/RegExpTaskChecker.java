package ru.inbox.savinov_vu.app.checker;

import org.springframework.stereotype.Component;
import ru.inbox.savinov_vu.app.checker.model.ConditionResult;
import ru.inbox.savinov_vu.app.checker.model.RegExpTaskResulter;
import ru.inbox.savinov_vu.app.checker.model.TaskCondition;

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


  public RegExpTaskResulter check(TaskCondition taskCondition) {

    var result = new RegExpTaskResulter();

    checkMatchedString(taskCondition, result);
    checkExcludeString(taskCondition, result);
    checkRequireSubstringinAnswer(taskCondition, result);
    checkExcludedAnswer(taskCondition, result);
    checkSpecialConditions(taskCondition, result);
    return result;
  }


  private boolean checkRequiredString(String requiredString, String regExp) {
    return regExp.contains(requiredString);
  }


  private boolean isEqualExcludedAnswer(String excludedAnswer, String regExp) {
    return regExp.equals(excludedAnswer);
  }


  private void checkSpecialConditions(TaskCondition taskCondition, RegExpTaskResulter result) {
    Integer maxLength = taskCondition.getMaxSymbols();
    if (nonNull(maxLength)) {
      result.setMaxSymbols(
        ConditionResult.of(String.valueOf(maxLength), taskCondition.getAnswer().length() <= maxLength));
    } else {
      result.setMaxSymbols(ConditionResult.of(null, true));
    }

    Integer minLength = taskCondition.getMinSymbols();
    if (nonNull(minLength)) {
      result.setMinSymbols(
        ConditionResult.of(String.valueOf(minLength), taskCondition.getAnswer().length() >= minLength));
    } else {
      result.setMinSymbols(ConditionResult.of(null, true));
    }

  }


  private void checkMatchedString(TaskCondition taskCondition, RegExpTaskResulter result) {
    List<ConditionResult> matchedResult = taskCondition.getMatchedStrings().stream()
      .map(v -> ConditionResult.of(v, checkMatches(v, taskCondition.getAnswer())))
      .collect(Collectors.toList());
    result.setMatchedStrings(matchedResult);
  }


  private void checkExcludeString(TaskCondition taskCondition, RegExpTaskResulter result) {
    List<ConditionResult> excludedStringsResult = taskCondition.getExcludedStrings().stream()
      .map(v -> ConditionResult.of(v, !checkMatches(v, taskCondition.getAnswer())))
      .collect(Collectors.toList());
    result.setExcludedStrings(excludedStringsResult);
  }


  private void checkRequireSubstringinAnswer(TaskCondition taskCondition, RegExpTaskResulter result) {
    List<ConditionResult> requireSubstringResult = taskCondition.getRequiredSubStrings().stream()
      .map(v -> ConditionResult.of(v, checkRequiredString(v, taskCondition.getAnswer())))
      .collect(Collectors.toList());
    result.setRequiredSubStrings(requireSubstringResult);
  }


  private void checkExcludedAnswer(TaskCondition taskCondition, RegExpTaskResulter result) {
    List<ConditionResult> excludeAnswerResult = taskCondition.getExcludedAnswers().stream()
      .map(v -> ConditionResult.of(v, !isEqualExcludedAnswer(v, taskCondition.getAnswer())))
      .collect(Collectors.toList());
    result.setExcludedAnswers(excludeAnswerResult);
  }


}
