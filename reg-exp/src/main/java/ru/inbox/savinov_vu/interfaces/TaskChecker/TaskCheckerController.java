package ru.inbox.savinov_vu.interfaces.TaskChecker;

import org.springframework.web.bind.annotation.*;
import ru.inbox.savinov_vu.checker.TaskResulter;

import java.util.List;



public interface TaskCheckerController<T> extends TaskChecker<T> {

    @Override
    @PutMapping("check/{id}")
    @CrossOrigin
    TaskResulter check(@PathVariable("id") Integer id, @RequestBody String answer);

    @Override
    @GetMapping("disabled")
    @CrossOrigin
    List<T> getDisabledTask();

}
