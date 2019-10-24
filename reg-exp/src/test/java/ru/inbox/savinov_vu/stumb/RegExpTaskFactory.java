package ru.inbox.savinov_vu.stumb;

import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;

import java.util.List;



public class RegExpTaskFactory {

  public static RegExpTask getRegExpTask() {
    RegExpTask regExpTask = new RegExpTask()
      .setMatchedStrings(List.of("qwe", "asd", "zxc"))
      .setExcludedStrings(List.of("b"))
      .setRequiredSubStrings(List.of("p"))
      .setExcludedAnswers(List.of("d"));
    return regExpTask;
  }


  public static RegExpTask getRegExpTaskWithId(Integer id) {
    RegExpTask regExpTask = getRegExpTask()
      .setId(id);
    return regExpTask;
  }

}
