package ru.inbox.savinov_vu.service;

import java.util.regex.Pattern;

public class RegExpCheckerService {

    public static boolean checkRegExp(String researchedString, String regExp) {
        var pattern = Pattern.compile(regExp);
        var matcher = pattern.matcher(researchedString);
        return matcher.matches();
    }


}
