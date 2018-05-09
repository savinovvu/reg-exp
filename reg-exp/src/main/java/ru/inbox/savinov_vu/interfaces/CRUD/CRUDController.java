package ru.inbox.savinov_vu.interfaces.CRUD;

import org.springframework.web.bind.annotation.*;

import java.util.List;



public interface CRUDController<T> extends CRUD<T> {


    @Override
    @PostMapping
    @CrossOrigin
    default void add(@RequestBody T t) {
        throw new UnsupportedOperationException();
    }


    @Override
    @GetMapping
    @CrossOrigin
    default List<T> getAll() {
        throw new UnsupportedOperationException();
    }


    @Override
    @GetMapping("parent/{id}")
    @CrossOrigin
    default List<T> getAllByParentId(@PathVariable("id") Integer id) {
        throw new UnsupportedOperationException();
    }


    @Override
    @GetMapping("/{id}")
    @CrossOrigin
    default T getById(@PathVariable("id") Integer id) {
        return null;
    }


    @Override
    @DeleteMapping("/{id}")
    @CrossOrigin
    default boolean delete(@PathVariable("id") Integer id) {
        throw new UnsupportedOperationException();
    }


    @Override
    @PutMapping
    @CrossOrigin
    default T update(@RequestBody T t) {
        throw new UnsupportedOperationException();
    }
}
