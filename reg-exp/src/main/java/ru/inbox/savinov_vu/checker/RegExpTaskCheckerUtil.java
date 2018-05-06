package ru.inbox.savinov_vu.checker;

import ru.inbox.savinov_vu.model.tasks.RegExpTask;

import java.util.regex.Pattern;

public interface RegExpTaskCheckerUtil {


    private static boolean checkMatches(String researchedString, String regExp) {
        var pattern = Pattern.compile(regExp);
        var matcher = pattern.matcher(researchedString);
        return matcher.matches();
    }


    private static boolean checkRequiredString(String requiredString, String regExp) {
        return regExp.contains(requiredString);
    }

    private static boolean isEqualExcludedAnswer(String excludedAnswer, String regExp) {
        return regExp.equals(excludedAnswer);
    }


    static TaskResulter check(RegExpTask regExpTask, String answer) {
        var result = new TaskResulter();

        regExpTask.getMatchStrings().stream()
                .filter(v -> !checkMatches(v, answer))
                .forEach(v -> result.setWrong(v, WrongCheckStatus.Unmatch));

        regExpTask.getExcludedStrings().stream()
                .filter(v -> checkMatches(v, answer))
                .forEach(v -> result.setWrong(v, WrongCheckStatus.Match));

        regExpTask.getRequiredSubStrings().stream()
                .filter(v -> !checkRequiredString(v, answer))
                .forEach(v -> result.setWrong(v, WrongCheckStatus.Unused));

        regExpTask.getExcludedAnswer().stream()
                .filter(v -> isEqualExcludedAnswer(v, answer))
                .forEach(v -> result.setWrong(v, WrongCheckStatus.Equals));

        return result;
    }
}
