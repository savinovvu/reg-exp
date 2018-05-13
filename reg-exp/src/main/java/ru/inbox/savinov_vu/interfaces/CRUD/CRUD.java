package ru.inbox.savinov_vu.interfaces.CRUD;

import ru.inbox.savinov_vu.interfaces.OperationResulter;

import java.util.List;



public interface CRUD<T> {

    default OperationResulter add(T t) {
        throw new UnsupportedOperationException();
    }


    default List<T> getAll() {
        throw new UnsupportedOperationException();
    }


    default List<T> getAllByParentId(Integer id) {
        throw new UnsupportedOperationException();
    }


    default T getById(Integer id) {
        throw new UnsupportedOperationException();
    }


    default boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }


    default T update(T t) {
        throw new UnsupportedOperationException();
    }
}
