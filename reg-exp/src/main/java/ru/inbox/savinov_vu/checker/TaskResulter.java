package ru.inbox.savinov_vu.checker;

import java.util.HashMap;
import java.util.Map;

public class TaskResulter {

    private boolean success = true;
    private Map<String, WrongCheckStatus> wrongMap = new HashMap<>();

    public boolean getSuccess() {
        return success;
    }

    public Map<String, WrongCheckStatus> getWrongMap() {
        return wrongMap;
    }


    public void setWrong(String wrongString, WrongCheckStatus status) {
        success = false;
        wrongMap.put(wrongString, status);
    }
}
