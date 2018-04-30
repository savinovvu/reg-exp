package ru.inbox.savinov_vu.interfaces;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CRUDService<T> extends CRUD<T>{

    @Transactional
    default void add(T t) {
        throw new UnsupportedOperationException();
    }

    @Transactional
    default List<T> getAll() {
        throw new UnsupportedOperationException();
    }

    @Transactional
    default T getById(Integer id){
        throw new UnsupportedOperationException();
    }

    @Transactional
    default boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Transactional
    default T update(T t) {
        throw new UnsupportedOperationException();
    }
}
