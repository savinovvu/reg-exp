package ru.inbox.savinov_vu.app.checker.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Data
public class TaskResulter {


  private List<ResultGroup> groups;


  public TaskResulter() {
    this.groups = new ArrayList<>();
  }


  public boolean getSuccess() {
    Optional<ConditionResult> first = groups.stream()
      .flatMap(v -> v.getConditions().stream())
      .filter(v -> !v.isResult())
      .findFirst();
    return first.isEmpty();
  }


}
