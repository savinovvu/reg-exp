package ru.inbox.savinov_vu.common.interfaces.numbered;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;



public interface NumberedController<T> {

    @GetMapping("byNumber/{number}")
    @CrossOrigin
    default ResponseEntity<T> getByNumber(HttpServletRequest request, Integer number, Principal principal) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("byNumber/{parentNumber}/{number}")
    @CrossOrigin
    default ResponseEntity<T> getByParentNumberAndByNumber(HttpServletRequest request, Integer parentNumber, Integer number, Principal principal) {
        throw new UnsupportedOperationException();
    }
}
