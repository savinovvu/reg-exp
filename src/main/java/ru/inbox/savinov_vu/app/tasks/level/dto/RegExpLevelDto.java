package ru.inbox.savinov_vu.app.tasks.level.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;



@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class RegExpLevelDto {

  private String id;

  private String description;

  private String number;

  private boolean solve;

  private int score;
}
