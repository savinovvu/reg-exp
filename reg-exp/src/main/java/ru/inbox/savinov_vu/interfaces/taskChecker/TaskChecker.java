package ru.inbox.savinov_vu.interfaces.taskChecker;

import ru.inbox.savinov_vu.checker.TaskResulter;

import java.util.List;



public interface TaskChecker<T> {

    TaskResulter check(Integer id, String answer);

    List<T> getDisabledTask();


}