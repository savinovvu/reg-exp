package ru.inbox.savinov_vu.interfaces;

import java.util.List;

public interface CRUD<T> {

    default void add(T t) {
        throw new UnsupportedOperationException();
    }

    default List<T> getAll() {
        throw new UnsupportedOperationException();
    }

    default T getById(Integer Id) {
        throw new UnsupportedOperationException();
    }

    default boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    default T update(T t) {
        throw new UnsupportedOperationException();
    }
}
