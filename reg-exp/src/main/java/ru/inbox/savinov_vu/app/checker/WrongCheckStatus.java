package ru.inbox.savinov_vu.app.checker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.inbox.savinov_vu.common.interfaces.enumInterfaces.DataEnum;



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


}
