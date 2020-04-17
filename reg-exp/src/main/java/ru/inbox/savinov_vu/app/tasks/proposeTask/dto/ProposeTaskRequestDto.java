package ru.inbox.savinov_vu.app.tasks.proposeTask.dto;

import lombok.Data;
import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.app.users.model.User;

import java.util.List;



@Data
public class ProposeTaskRequestDto {

  private String title;

  private String description;

  private Integer score;

  private String answer;

  private List<String> matchedStrings;

  private List<String> excludedStrings;

  private List<String> requiredSubStrings;

  private List<String> excludedAnswers;

  private Integer maxSymbols;

  private Integer minSymbols;


  public RegExpTask toRegExpTask(User author) {
    RegExpTask regExpTask = new RegExpTask()
      .setEnTitle(title)
      .setEnDescription(description)
      .setScore(score)
      .setAnswers(List.of(answer))
      .setMatchedStrings(matchedStrings)
      .setExcludedStrings(excludedStrings)
      .setRequiredSubStrings(requiredSubStrings)
      .setExcludedAnswers(excludedAnswers)
      .setMaxAnswerLength(maxSymbols)
      .setMinAnswerLength(minSymbols)
      .setAuthor(author)
      .setEnabled(false);
    return regExpTask;
  }


}
