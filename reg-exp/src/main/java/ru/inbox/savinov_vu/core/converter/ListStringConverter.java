package ru.inbox.savinov_vu.core.converter;

import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static ru.inbox.savinov_vu.common.constant.StringConstants.EMPTY;



@Converter
public class ListStringConverter implements AttributeConverter<List<String>, String> {


  @Override
  public String convertToDatabaseColumn(List<String> attribute) {
    return CollectionUtils.isEmpty(attribute) ? EMPTY : attribute.stream().collect(joining(" //, ", "", ""));
  }


  @Override
  public List<String> convertToEntityAttribute(String dbData) {
    return StringUtils.isNotEmpty(dbData) ? Arrays.stream(dbData.split(" //, "))
      .collect(Collectors.toList()) : Collections.emptyList();
  }
}
