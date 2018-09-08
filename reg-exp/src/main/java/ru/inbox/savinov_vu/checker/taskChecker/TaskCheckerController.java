package ru.inbox.savinov_vu.checker.taskChecker;

import org.springframework.web.bind.annotation.*;
import ru.inbox.savinov_vu.checker.TaskResulter;

import javax.servlet.http.HttpServletRequest;
import java.util.List;



public interface TaskCheckerController<T> {

    @PutMapping("check/{id}")
    @CrossOrigin
    TaskResulter check(HttpServletRequest request, Integer id, String answer);

    @GetMapping("disabled")
    @CrossOrigin
    List<T> getDisabledTask();

}
