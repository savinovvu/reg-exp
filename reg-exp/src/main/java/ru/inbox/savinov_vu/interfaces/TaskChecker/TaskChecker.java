package ru.inbox.savinov_vu.interfaces.TaskChecker;

import ru.inbox.savinov_vu.checker.TaskResulter;

@FunctionalInterface
public interface TaskChecker {
    TaskResulter check(Integer id, String answer);
}
