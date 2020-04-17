package ru.inbox.savinov_vu.app.checker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.inbox.savinov_vu.app.tasks.proposeTask.dto.ProposeTaskRequestDto;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;

import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskCondition {

  private List<String> matchedStrings;

  private List<String> excludedStrings;

  private List<String> requiredSubStrings;

  private List<String> excludedAnswers;

  private Integer maxSymbols;

  private Integer minSymbols;

  private String answer;


  public static TaskCondition of(RegExpTask regExpTask, String answer) {
    TaskCondition taskCondition = new TaskCondition();
    taskCondition.matchedStrings = regExpTask.getMatchedStrings();
    taskCondition.excludedStrings = regExpTask.getExcludedStrings();
    taskCondition.requiredSubStrings = regExpTask.getRequiredSubStrings();
    taskCondition.excludedAnswers = regExpTask.getExcludedAnswers();
    taskCondition.maxSymbols = regExpTask.getMaxAnswerLength();
    taskCondition.minSymbols = regExpTask.getMinAnswerLength();
    taskCondition.answer = answer;
    return taskCondition;
  }


  public static TaskCondition of(ProposeTaskRequestDto proposeTaskRequestDto) {
    TaskCondition taskCondition = new TaskCondition();
    taskCondition.matchedStrings = proposeTaskRequestDto.getMatchedStrings();
    taskCondition.excludedStrings = proposeTaskRequestDto.getExcludedStrings();
    taskCondition.requiredSubStrings = proposeTaskRequestDto.getRequiredSubStrings();
    taskCondition.excludedAnswers = proposeTaskRequestDto.getExcludedAnswers();
    taskCondition.maxSymbols = proposeTaskRequestDto.getMaxSymbols();
    taskCondition.minSymbols = proposeTaskRequestDto.getMinSymbols();
    taskCondition.answer = proposeTaskRequestDto.getAnswer();
    return taskCondition;
  }

}
