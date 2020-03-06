package ru.inbox.savinov_vu.app.checker.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;



@Data
public class TaskResulter {


  private List<ConditionResult> matchedStrings;

  private List<ConditionResult> excludedStrings;

  private List<ConditionResult> requiredSubStrings;

  private List<ConditionResult> excludedAnswers;


  public TaskResulter() {
    this.matchedStrings = new ArrayList<>();
    this.excludedStrings = new ArrayList<>();
    this.requiredSubStrings = new ArrayList<>();
    this.excludedAnswers = new ArrayList<>();
  }


  public boolean getSuccess() {
    Optional<ConditionResult> first =
      Stream.of(matchedStrings, excludedStrings, requiredSubStrings, excludedAnswers)
        .flatMap(Collection::stream)
        .filter(v -> !v.isResult())
        .findFirst();
    return first.isEmpty();
  }


}
