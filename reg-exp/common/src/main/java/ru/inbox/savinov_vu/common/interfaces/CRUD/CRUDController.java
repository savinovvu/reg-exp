package ru.inbox.savinov_vu.common.interfaces.CRUD;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;



public interface CRUDController<T> {


    @PostMapping
    @CrossOrigin
    default ResponseEntity add(HttpServletRequest request, T t, Principal principal) {
        throw new UnsupportedOperationException();
    }


    @GetMapping
    @CrossOrigin
    default ResponseEntity<List<T>> getAll(HttpServletRequest request, Principal principal) {
        throw new UnsupportedOperationException();
    }


    @GetMapping("parent/{id}")
    @CrossOrigin
    default ResponseEntity<List<T>> getAllByParentId(HttpServletRequest request, Integer id, Principal principal) {
        throw new UnsupportedOperationException();
    }


    @GetMapping("{id}")
    @CrossOrigin
    default ResponseEntity<T> getById(HttpServletRequest request, Integer id, Principal principal) {
        throw new UnsupportedOperationException();
    }


    @DeleteMapping("{id}")
    @CrossOrigin
    default ResponseEntity delete(HttpServletRequest request, Integer id, Principal principal) {
        throw new UnsupportedOperationException();
    }


    @PutMapping
    @CrossOrigin
    default ResponseEntity<T> update(HttpServletRequest request, T t, Principal principal) {
        throw new UnsupportedOperationException();
    }
}
