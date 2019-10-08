package ru.inbox.savinov_vu.data;

import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;
import ru.inbox.savinov_vu.app.users.model.User;

import java.util.List;



public class DataClassesFactory {

  public static RegExpTask getRegExpTask() {
    RegExpTask regExpTask = new RegExpTask()
      .setMatchedStrings(List.of("qwe","asd", "zxc"))
      .setExcludedStrings(List.of("b"))
      .setRequiredSubStrings(List.of("p"))
      .setExcludedAnswers(List.of("d"));
    return regExpTask;
  }


  public static User getUser() {
    return null;
  }
}
