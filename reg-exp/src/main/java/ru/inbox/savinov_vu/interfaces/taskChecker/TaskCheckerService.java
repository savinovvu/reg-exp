package ru.inbox.savinov_vu.interfaces.taskChecker;

import org.springframework.transaction.annotation.Transactional;
import ru.inbox.savinov_vu.checker.TaskResulter;

import java.util.List;



public interface TaskCheckerService<T> {

    @Transactional(readOnly = true)
    TaskResulter check(Integer id, String answer);


    @Transactional(readOnly = true)
    List<T> getDisabledTask();
}
