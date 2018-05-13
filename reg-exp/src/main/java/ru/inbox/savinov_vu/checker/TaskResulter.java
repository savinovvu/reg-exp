package ru.inbox.savinov_vu.checker;

import ru.inbox.savinov_vu.interfaces.OperationResulter;

import java.util.*;



public class TaskResulter implements OperationResulter<Map<WrongCheckStatus, List<String>>> {

    private boolean success = true;

    private Map<WrongCheckStatus, List<String>> wrongMap = new HashMap<>();


    public boolean getSuccess() {
        return success;
    }


    public TaskResulter setWrong(String wrongString, WrongCheckStatus status) {
        success = false;
        wrongMap.merge(status, new ArrayList<>(List.of(wrongString)),
                (value, newValue) -> {
                    value.add(wrongString);
                    return value;
                });
        return this;
    }



    @Override
    public Map<WrongCheckStatus, List<String>> getResult() {
        return wrongMap;
    }
}
