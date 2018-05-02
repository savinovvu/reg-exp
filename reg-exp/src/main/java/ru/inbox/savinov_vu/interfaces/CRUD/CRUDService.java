package ru.inbox.savinov_vu.interfaces.CRUD;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CRUDService<T> extends CRUD<T>{

    default void add(T t) {
        throw new UnsupportedOperationException();
    }

    default List<T> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    default List<T> getAllByParentId(Integer id) {
        throw new UnsupportedOperationException();
    }

    default T getById(Integer id){
        throw new UnsupportedOperationException();
    }

    @Transactional
    @Modifying
    default boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    default T update(T t) {
        throw new UnsupportedOperationException();
    }
}
