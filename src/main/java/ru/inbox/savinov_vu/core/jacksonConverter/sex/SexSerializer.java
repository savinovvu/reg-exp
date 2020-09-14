package ru.inbox.savinov_vu.core.jacksonConverter.sex;

import com.fasterxml.jackson.databind.util.StdConverter;
import ru.inbox.savinov_vu.app.users.model.Sex;



public class SexSerializer extends StdConverter<Sex, String> {

  @Override
  public String convert(Sex value) {
    return value.getValue();
  }
}
