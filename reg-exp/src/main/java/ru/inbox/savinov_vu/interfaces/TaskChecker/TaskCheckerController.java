package ru.inbox.savinov_vu.interfaces.TaskChecker;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.inbox.savinov_vu.checker.TaskResulter;

@FunctionalInterface
public interface TaskCheckerController extends TaskChecker {

    @Override
    @PutMapping("check/{id}")
    @CrossOrigin
    TaskResulter check(@PathVariable("id") Integer id, @RequestBody String answer);
}
