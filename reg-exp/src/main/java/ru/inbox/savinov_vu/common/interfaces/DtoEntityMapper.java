package ru.inbox.savinov_vu.common.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;



public interface DtoEntityMapper<D, E> {

  default List<D> mapEntityToDto(List<E> entityList) {
    if (isNull(entityList) || entityList.isEmpty()) {
      return new ArrayList<>();
    }
    List<D> result = entityList.stream()
      .map(this::mapEntityToDto)
      .collect(Collectors.toList());
    return result;
  }

  D mapEntityToDto(E entity);

  default List<E> mapDtoToEntity(List<D> dtoList) {
    if (isNull(dtoList) || dtoList.isEmpty()) {
      return new ArrayList<>();
    }
    List<E> result = dtoList.stream()
      .map(this::mapDtoToEntity)
      .collect(Collectors.toList());
    return result;
  }

  E mapDtoToEntity(D dto);

}
