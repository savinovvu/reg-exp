package ru.inbox.savinov_vu.common.interfaces.enumInterfaces;

import com.fasterxml.jackson.annotation.JsonFormat;



@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface DataEnum<V> extends ValueSource<V>, LabelSource {

}
