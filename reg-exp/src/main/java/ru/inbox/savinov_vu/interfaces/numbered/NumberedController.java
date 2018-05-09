package ru.inbox.savinov_vu.interfaces.numbered;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;



public interface NumberedController<T> extends Numbered<T>{

    @Override
    @GetMapping("byNumber/{number}")
    @CrossOrigin
    default T getByNumber(@PathVariable("number") Integer number) {
        throw new UnsupportedOperationException();
    }

    @Override
    @GetMapping("byNumber/{parentNumber}/{number}")
    @CrossOrigin
    default List<T> getByParentNumberAndByNumber(@PathVariable("parentNumber") Integer parentNumber, @PathVariable("number") Integer number){
        throw new UnsupportedOperationException();
    }
}
