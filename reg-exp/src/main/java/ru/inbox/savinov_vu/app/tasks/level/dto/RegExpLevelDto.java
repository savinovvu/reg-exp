package ru.inbox.savinov_vu.app.tasks.level.dto;

import lombok.Data;
import lombok.experimental.Accessors;



@Data
@Accessors(chain = true)
public class RegExpLevelDto {

  private String id;

  private String description;

  private String number;

  private boolean solve;

  private int score;
}
