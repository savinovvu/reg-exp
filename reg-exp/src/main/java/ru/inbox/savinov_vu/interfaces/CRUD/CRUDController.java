package ru.inbox.savinov_vu.interfaces.CRUD;

import org.springframework.web.bind.annotation.*;
import ru.inbox.savinov_vu.interfaces.OperationResulter;

import java.util.List;



public interface CRUDController<T> extends CRUD<T> {


    @Override
    @PostMapping
    @CrossOrigin
    default OperationResulter add(T t) {
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
    default List<T> getAllByParentId(Integer id) {
        throw new UnsupportedOperationException();
    }


    @Override
    @GetMapping("{id}")
    @CrossOrigin
    default T getById(Integer id) {
        throw new UnsupportedOperationException();
    }


    @Override
    @DeleteMapping("{id}")
    @CrossOrigin
    default boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }


    @Override
    @PutMapping
    @CrossOrigin
    default T update(T t) {
        throw new UnsupportedOperationException();
    }
}
