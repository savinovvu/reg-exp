package ru.inbox.savinov_vu.app.checker.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;



@Data
public class RegExpTaskResulter {


  private List<ConditionResult> matchedStrings;

  private List<ConditionResult> excludedStrings;

  private List<ConditionResult> requiredSubStrings;

  private List<ConditionResult> excludedAnswers;

  private ConditionResult maxSymbols;

  private ConditionResult minSymbols;


  public RegExpTaskResulter() {
    this.matchedStrings = new ArrayList<>();
    this.excludedStrings = new ArrayList<>();
    this.requiredSubStrings = new ArrayList<>();
    this.excludedAnswers = new ArrayList<>();
  }


  public boolean getSuccess() {
    List<ConditionResult> specialConditions = getSpecialConditions();

    Optional<ConditionResult> first =
      Stream.of(matchedStrings, excludedStrings, requiredSubStrings, excludedAnswers, specialConditions)
        .flatMap(Collection::stream)
        .filter(v -> !v.isResult())
        .findFirst();
    return first.isEmpty();
  }


  public List<ConditionResult> getSpecialConditions() {
    ConditionResult max = ConditionResult.of("Max " + maxSymbols.getCondition() + " symbols", maxSymbols.isResult());
    ConditionResult min = ConditionResult.of("Min " + minSymbols.getCondition() + " symbols", minSymbols.isResult());

    var result = new ArrayList<ConditionResult>();

    if (Objects.nonNull(maxSymbols.getCondition())) {
      result.add(max);
    }

    if (Objects.nonNull(minSymbols.getCondition())) {
      result.add(min);
    }
    return result;
  }


}
