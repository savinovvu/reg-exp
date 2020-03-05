package ru.inbox.savinov_vu.app.checker.model;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor(staticName = "of")
public class ConditionResult {

  private String condition;

  private boolean result;

}
