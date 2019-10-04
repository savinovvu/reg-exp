package ru.inbox.savinov_vu.app.checker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.inbox.savinov_vu.common.interfaces.enumInterfaces.DataEnum;

import java.util.Arrays;

import static java.util.Objects.isNull;



@AllArgsConstructor
@Getter
public enum WrongCheckStatus implements DataEnum<String> {
  UNMATCH("unmatch", "Unmatched", "Не совпало"),
  MATCH("match", "Match", "совпало"),
  UNUSED("unused", "Unused", "Не использовано"),
  EQUALS("equals", "Equals", "Равно"),
  NOT_ANSWER("not_answer", "Not Answer", "Нет ответа");

  private String value;

  private String enLabel;

  private String ruLabel;


  public static WrongCheckStatus getByValue(String value) {
    if (isNull(value)) {
      return null;
    }
    return Arrays.stream(values()).filter(v -> v.getValue().equals(value))
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException(String.format("Wrong %s value: %s",
        WrongCheckStatus.class.getName(), value)));
  }
}
