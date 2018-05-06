package ru.inbox.savinov_vu.checker;

import java.util.*;



public class TaskResulter {

    private boolean success = true;

    private Map<WrongCheckStatus, List<String>> wrongMap = new HashMap<>();


    public boolean getSuccess() {
        return success;
    }


    public Map<WrongCheckStatus, List<String>> getWrongMap() {
        return wrongMap;
    }


    public void setWrong(String wrongString, WrongCheckStatus status) {
        success = false;
        wrongMap.merge(status, new ArrayList<>(List.of(wrongString)),
                (value, newValue) -> {
                    value.add(wrongString);
                    return value;
                });
    }
}
