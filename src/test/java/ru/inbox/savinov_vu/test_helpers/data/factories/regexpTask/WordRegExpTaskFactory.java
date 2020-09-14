package ru.inbox.savinov_vu.test_helpers.data.factories.regexpTask;

import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;

import java.util.List;



public class WordRegExpTaskFactory extends BaseRegExpTaskFactory {

  public static RegExpTask getWordTask() {
    var regExpTask = of()
      .setMatchedStrings(List.of("word"))
      .setExcludedStrings(List.of())
      .setRequiredSubStrings(List.of("word"))
      .setExcludedAnswers(List.of())
      .setAnswers(List.of("word"));
    return regExpTask;
  }

}
