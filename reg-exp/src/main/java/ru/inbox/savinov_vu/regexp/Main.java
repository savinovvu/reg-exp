package ru.inbox.savinov_vu.regexp;

import ru.inbox.savinov_vu.service.RegExpTaskService;

public class Main {
    public static void main(String[] args) {
        System.out.println(RegExpTaskService.checkRegExp("name", "name"));
    }
}
