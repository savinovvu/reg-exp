package ru.inbox.savinov_vu.interfaces.taskChecker;

import org.springframework.transaction.annotation.Transactional;
import ru.inbox.savinov_vu.checker.TaskResulter;

import java.util.List;



public interface TaskCheckerService<T> extends TaskChecker<T> {

    @Override
    @Transactional(readOnly = true)
    TaskResulter check(Integer id, String answer);


    @Override
    @Transactional(readOnly = true)
    List<T> getDisabledTask();
}
