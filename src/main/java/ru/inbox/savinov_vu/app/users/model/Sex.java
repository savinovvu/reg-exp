package ru.inbox.savinov_vu.app.users.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.inbox.savinov_vu.common.interfaces.enumInterfaces.DataEnum;

import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;



@AllArgsConstructor
@Getter
public enum Sex implements DataEnum<String> {
  MAN("man", "Man", "М"),
  WOMAN("woman", "Woman", "Ж");


  private final String value;

  private final String enLabel;

  private final String ruLabel;


  public static Sex getByValue(String value) {
    if (isNull(value)) {
      return null;
    }
    return getAll().stream().filter(v -> v.getValue().equals(value))
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException(String.format("Wrong %s value: %s",
        Sex.class.getName(), value)));
  }


  public static List<Sex> getAll() {
    return Arrays.asList(values());
  }

}
