package ru.inbox.savinov_vu.regexp;

import ru.inbox.savinov_vu.service.RegExpCheckerService;

public class Main {
    public static void main(String[] args) {
        System.out.println(RegExpCheckerService.checkRegExp("name", "name"));
    }
}
