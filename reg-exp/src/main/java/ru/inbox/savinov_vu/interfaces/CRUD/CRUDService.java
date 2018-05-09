package ru.inbox.savinov_vu.interfaces.CRUD;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



public interface CRUDService<T> extends CRUD<T> {

    @Override
    @Transactional(readOnly = false)
    default void add(T t) {
        throw new UnsupportedOperationException();
    }


    @Override
    @Transactional(readOnly = true)
    default List<T> getAll() {
        throw new UnsupportedOperationException();
    }


    @Override
    @Transactional(readOnly = true)
    default List<T> getAllByParentId(Integer id) {
        throw new UnsupportedOperationException();
    }


    @Override
    @Transactional(readOnly = true)
    default T getById(Integer id) {
        throw new UnsupportedOperationException();
    }


    @Override
    @Transactional(readOnly = false)
    @Modifying
    default boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }


    @Override
    @Transactional(readOnly = false)
    default T update(T t) {
        throw new UnsupportedOperationException();
    }

}
