package ru.inbox.savinov_vu.test_helpers.data.factories.regexpTask;

import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;



public class BaseRegExpTaskFactory {

  public static RegExpTask of() {
    var simpleTask = new RegExpTask()
      .setNumber(1)
      .setRuTitle("Заголовок")
      .setEnTitle("Title")
      .setRuDescription("Описание")
      .setEnDescription("description");
    return simpleTask;

  }

}
