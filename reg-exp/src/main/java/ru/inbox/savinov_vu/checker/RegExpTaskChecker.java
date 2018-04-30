package ru.inbox.savinov_vu.checker;

import ru.inbox.savinov_vu.interfaces.TaskService;

import java.util.regex.Pattern;

public class RegExpTaskChecker implements TaskService {

    private static boolean checkRegExp(String researchedString, String regExp) {
        var pattern = Pattern.compile(regExp);
        var matcher = pattern.matcher(researchedString);
        return matcher.matches();
    }


    @Override
    public boolean check() {
        return false;
    }
}
