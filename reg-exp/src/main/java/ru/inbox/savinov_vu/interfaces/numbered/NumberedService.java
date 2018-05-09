package ru.inbox.savinov_vu.interfaces.numbered;

import org.springframework.transaction.annotation.Transactional;



public interface NumberedService<T> extends Numbered<T> {

    @Override
    @Transactional(readOnly = true)
    default T getByNumber(Integer number) {
        throw new UnsupportedOperationException();
    }


    @Override
    @Transactional(readOnly = true)
    default T getByParentNumberAndByNumber(Integer parentNumber, Integer number){
        throw new UnsupportedOperationException();
    }

}
