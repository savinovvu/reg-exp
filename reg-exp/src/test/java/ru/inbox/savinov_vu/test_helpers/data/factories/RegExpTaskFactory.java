package ru.inbox.savinov_vu.test_helpers.data.factories;

import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;

import java.util.List;



public class RegExpTaskFactory {

  public static RegExpTask getRegExpTaskWithMistakes() {
    RegExpTask regExpTask = new RegExpTask()
      .setMatchedStrings(List.of("qwe", "asd", "zxc"))
      .setExcludedStrings(List.of("b"))
      .setRequiredSubStrings(List.of("p"))
      .setExcludedAnswers(List.of("d"))
      .setNumber(1);
    return regExpTask;
  }


  public static RegExpTask getRegExpTask() {
    RegExpTask regExpTask = new RegExpTask()
      .setMatchedStrings(List.of("Cat say mew"))
      .setExcludedStrings(List.of("say", "mew"))
      .setRequiredSubStrings(List.of("Cat"))
      .setExcludedAnswers(List.of("Cat say mew"));
    return regExpTask;
  }


  public static RegExpTask getRegExpTaskWithNumber(Integer number) {
    RegExpTask regExpTask = getRegExpTask()
      .setNumber(number);
    return regExpTask;
  }


  public static RegExpTask getRegExpTaskWithId(Integer id) {
    RegExpTask regExpTask = getRegExpTask()
      .setId(id);
    return regExpTask;
  }




}
