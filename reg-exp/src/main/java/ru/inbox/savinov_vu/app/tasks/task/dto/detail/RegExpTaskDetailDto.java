package ru.inbox.savinov_vu.app.tasks.task.dto.detail;

import lombok.Data;

import java.util.List;


@Data
public class RegExpTaskDetailDto {

  private String id;

  private String title;

  private String description;

  private Integer number;

  private List<String> matchedStrings;

  private List<String> excludedStrings;

  private List<String> requiredSubStrings;

  private List<String> excludedAnswers;

  private int score;

}
