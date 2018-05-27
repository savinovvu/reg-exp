package ru.inbox.savinov_vu.interfaces.numbered;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;



public interface NumberedController<T> {

    @GetMapping("byNumber/{number}")
    @CrossOrigin
    default T getByNumber(HttpServletRequest request, Integer number) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("byNumber/{parentNumber}/{number}")
    @CrossOrigin
    default T getByParentNumberAndByNumber(HttpServletRequest request, Integer parentNumber, Integer number) {
        throw new UnsupportedOperationException();
    }
}
