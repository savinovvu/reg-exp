package ru.inbox.savinov_vu.interfaces.numbered;

public interface Numbered<T> {


    default T getByNumber(Integer number) {
        throw new UnsupportedOperationException();
    }


    default T getByParentNumberAndByNumber(Integer parentNumber, Integer number) {
        throw new UnsupportedOperationException();
    }


}
