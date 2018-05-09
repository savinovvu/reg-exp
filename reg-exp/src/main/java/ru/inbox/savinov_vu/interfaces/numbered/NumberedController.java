package ru.inbox.savinov_vu.interfaces.numbered;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;



public interface NumberedController<T> extends Numbered<T>{

    @Override
    @GetMapping("byNumber/{number}")
    @CrossOrigin
    default T getByNumber(Integer number) {
        throw new UnsupportedOperationException();
    }

    @Override
    @GetMapping("byNumber/{parentNumber}/{number}")
    @CrossOrigin
    default List<T> getByParentNumberAndByNumber(Integer parentNumber, Integer number){
        throw new UnsupportedOperationException();
    }
}
