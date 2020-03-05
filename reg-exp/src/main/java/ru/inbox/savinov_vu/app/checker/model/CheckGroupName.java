package ru.inbox.savinov_vu.app.checker.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;



@Getter
@AllArgsConstructor
public enum CheckGroupName {

  MATCH("match"),
  EXCLUDE("exclude"),
  REQUIRE_SUBSTRING("requireSubstring"),
  EXCLUDE_ANSWER("excludeAnswer");

  @JsonValue
  private String value;


}
