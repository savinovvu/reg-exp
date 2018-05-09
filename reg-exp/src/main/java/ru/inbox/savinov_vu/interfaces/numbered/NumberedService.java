package ru.inbox.savinov_vu.interfaces.numbered;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;



public interface NumberedService<T> extends Numbered<T> {

    @Override
    @Transactional(readOnly = true)
    default T getByNumber(Integer number) {
        throw new UnsupportedOperationException();
    }


    @Override
    @Transactional(readOnly = true)
    default List<T> getByParentNumberAndByNumber(Integer parentNumber, Integer number){
        throw new UnsupportedOperationException();
    }

}
