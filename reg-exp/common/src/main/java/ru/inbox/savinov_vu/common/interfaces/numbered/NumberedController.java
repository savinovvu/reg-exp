package ru.inbox.savinov_vu.common.interfaces.numbered;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

import static ru.inbox.savinov_vu.common.constant.PathConstant.NUMBERED_WITH_PARAM;
import static ru.inbox.savinov_vu.common.constant.PathConstant.NUMBERED_WITH_PARENT_PARAM;



public interface NumberedController<T> {

    @GetMapping(NUMBERED_WITH_PARAM)
    @CrossOrigin
    default ResponseEntity<T> getByNumber(HttpServletRequest request, Integer number, Principal principal) {
        throw new UnsupportedOperationException();
    }

    @GetMapping(NUMBERED_WITH_PARENT_PARAM)
    @CrossOrigin
    default ResponseEntity<T> getByParentNumberAndByNumber(HttpServletRequest request, Integer parentNumber, Integer number, Principal principal) {
        throw new UnsupportedOperationException();
    }
}
