package ru.inbox.savinov_vu.core.converter;

import ru.inbox.savinov_vu.app.users.model.Sex;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static java.util.Objects.isNull;



@Converter
public class SexConverter implements AttributeConverter<Sex, String> {


  @Override
  public String convertToDatabaseColumn(Sex attribute) {
    return isNull(attribute) ? null : attribute.getValue();
  }


  @Override
  public Sex convertToEntityAttribute(String dbData) {
    return isNull(dbData) ? null : Sex.getByValue(dbData);
  }
}
