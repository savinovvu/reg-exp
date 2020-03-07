package ru.inbox.savinov_vu.app.tasks.task.dto.list;

import lombok.Data;
import lombok.experimental.Accessors;



@Data
@Accessors(chain = true)
public class RegExpTaskListDto {

  private String id;

  private String title;

  private String description;

  private Integer number;

  private boolean solve;

  private int score;

}
