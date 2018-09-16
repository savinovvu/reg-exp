package ru.inbox.savinov_vu.checker.taskChecker;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.inbox.savinov_vu.checker.TaskResulter;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;



public interface TaskCheckerController<T> {

    @PutMapping("check/{id}")
    @CrossOrigin
    ResponseEntity<TaskResulter> check(HttpServletRequest request, Integer id, String answer, Principal principal);

    @GetMapping("disabled")
    @CrossOrigin
    ResponseEntity<List<T>> getDisabledTask(Principal principal);

}
