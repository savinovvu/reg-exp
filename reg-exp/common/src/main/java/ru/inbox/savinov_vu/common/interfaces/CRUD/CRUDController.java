package ru.inbox.savinov_vu.common.interfaces.CRUD;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import ru.inbox.savinov_vu.common.interfaces.OperationResulter;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


// todo: implement with return type: ResponseEntity
public interface CRUDController<T> {


    @PostMapping
    @CrossOrigin
    default OperationResulter add(HttpServletRequest request, T t) {
        throw new UnsupportedOperationException();
    }


    @GetMapping
    @CrossOrigin
    default List<T> getAll(HttpServletRequest request) {
        throw new UnsupportedOperationException();
    }


    @GetMapping("parent/{id}")
    @CrossOrigin
    default List<T> getAllByParentId(HttpServletRequest request, Integer id) {
        throw new UnsupportedOperationException();
    }


    @GetMapping("{id}")
    @CrossOrigin
    default T getById(HttpServletRequest request, Integer id) {
        throw new UnsupportedOperationException();
    }


    @DeleteMapping("{id}")
    @CrossOrigin
    default boolean delete(HttpServletRequest request, Integer id) {
        throw new UnsupportedOperationException();
    }


    @PutMapping
    @CrossOrigin
    default T update(HttpServletRequest request, T t) {
        throw new UnsupportedOperationException();
    }
}