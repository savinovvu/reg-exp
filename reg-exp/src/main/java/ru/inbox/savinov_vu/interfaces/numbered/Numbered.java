package ru.inbox.savinov_vu.interfaces.numbered;

import java.util.List;



public interface Numbered<T> {


    default T getByNumber(Integer number) {
        throw new UnsupportedOperationException();
    }


    default List<T> getByParentNumberAndByNumber(Integer parentNumber, Integer number) {
        throw new UnsupportedOperationException();
    }


}
