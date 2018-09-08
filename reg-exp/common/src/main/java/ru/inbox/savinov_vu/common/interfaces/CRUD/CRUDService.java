package ru.inbox.savinov_vu.common.interfaces.CRUD;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import ru.inbox.savinov_vu.common.interfaces.OperationResulter;

import java.util.List;



public interface CRUDService<T> {

    @Transactional(readOnly = false)
    default OperationResulter add(T t) {
        throw new UnsupportedOperationException();
    }


    @Transactional(readOnly = true)
    default List<T> getAll() {
        throw new UnsupportedOperationException();
    }


    @Transactional(readOnly = true)
    default List<T> getAllByParentId(Integer id) {
        throw new UnsupportedOperationException();
    }


    @Transactional(readOnly = true)
    default T getById(Integer id) {
        throw new UnsupportedOperationException();
    }


    @Transactional(readOnly = false)
    @Modifying
    default boolean delete(Integer id) {
        throw new UnsupportedOperationException();
    }


    @Transactional(readOnly = false)
    default T update(T t) {
        throw new UnsupportedOperationException();
    }

    @Transactional(readOnly = false)
    default OperationResulter add(T t, Integer userId) {
        throw new UnsupportedOperationException();
    }


    @Transactional(readOnly = true)
    default List<T> getAll(Integer userId) {
        throw new UnsupportedOperationException();
    }


    @Transactional(readOnly = true)
    default List<T> getAllByParentId(Integer id, Integer userId) {
        throw new UnsupportedOperationException();
    }


    @Transactional(readOnly = true)
    default T getById(Integer id, Integer userId) {
        throw new UnsupportedOperationException();
    }


    @Transactional(readOnly = false)
    @Modifying
    default boolean delete(Integer id, Integer userId) {
        throw new UnsupportedOperationException();
    }


    @Transactional(readOnly = false)
    default T update(T t, Integer userId) {
        throw new UnsupportedOperationException();
    }

}
