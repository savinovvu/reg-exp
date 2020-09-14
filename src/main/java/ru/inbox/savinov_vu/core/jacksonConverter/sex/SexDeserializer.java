package ru.inbox.savinov_vu.core.jacksonConverter.sex;

import com.fasterxml.jackson.databind.util.StdConverter;
import ru.inbox.savinov_vu.app.users.model.Sex;



public class SexDeserializer extends StdConverter<String, Sex> {

  @Override
  public Sex convert(String value) {
    return Sex.getByValue(value);
  }
}
