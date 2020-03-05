package ru.inbox.savinov_vu.app.checker.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor(staticName = "of")
public class ResultGroup {

  private CheckGroupName group;

  private List<ConditionResult> conditions;





}
