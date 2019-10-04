package ru.inbox.savinov_vu.core.converter;

import ru.inbox.savinov_vu.app.checker.WrongCheckStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static java.util.Objects.isNull;



@Converter
public class BookingStatusConverter implements AttributeConverter<WrongCheckStatus, String> {


    @Override
    public String convertToDatabaseColumn(WrongCheckStatus attribute) {
        return isNull(attribute) ? null : attribute.getValue();
    }


    @Override
    public WrongCheckStatus convertToEntityAttribute(String dbData) {
        return isNull(dbData) ? null : WrongCheckStatus.getByValue(dbData);
    }
}
