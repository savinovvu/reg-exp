package ru.inbox.savinov_vu.interfaces.TaskChecker;

import org.springframework.transaction.annotation.Transactional;
import ru.inbox.savinov_vu.checker.TaskResulter;

@FunctionalInterface
public interface TaskCheckerService extends TaskChecker {

    @Override
    @Transactional(readOnly = true)
    TaskResulter check(Integer id, String answer);
}
