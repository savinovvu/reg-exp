package ru.inbox.savinov_vu.interfaces;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CRUDController<T> extends CRUD<T> {


    @Override
    @PostMapping
    default void add(@RequestBody T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    @GetMapping
    default List<T> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    @GetMapping("/{id}")
    default T getById(@PathVariable("id") Integer Id) {
        return null;
    }

    @Override
    @DeleteMapping
    default boolean delete(@RequestBody Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    @PutMapping
    default T update(@RequestBody T t) {
        throw new UnsupportedOperationException();
    }
}
