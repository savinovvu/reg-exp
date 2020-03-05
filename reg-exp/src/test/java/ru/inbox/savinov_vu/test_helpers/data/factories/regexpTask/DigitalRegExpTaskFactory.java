package ru.inbox.savinov_vu.test_helpers.data.factories.regexpTask;

import ru.inbox.savinov_vu.app.tasks.task.model.RegExpTask;

import java.util.List;



public class DigitalRegExpTaskFactory extends BaseRegExpTaskFactory {


  public static RegExpTask getOneDecimalRange() {
    var regExpTask = of()
      .setMatchedStrings(List.of("1", "2", "3", "8"))
      .setExcludedStrings(List.of("a", "12", "123", ""))
      .setRequiredSubStrings(List.of())
      .setExcludedAnswers(List.of("\\d", "\\d{0,1}", "\\d{1,1}"))
      .setAnswers(List.of("[0-9]"));
    return regExpTask;
  }


  public static RegExpTask getThreeDecimalRange() {
    var regExpTask = of()
      .setMatchedStrings(List.of("111", "222", "888"))
      .setExcludedStrings(List.of("a", "abc", "1234", "12"))
      .setRequiredSubStrings(List.of())
      .setExcludedAnswers(List.of("\\d{3}", "\\d{3,3}"))
      .setAnswers(List.of("[0-9]{3}"));
    return regExpTask;
  }


  public static RegExpTask getMultipleDecimalRange() {
    var regExpTask = of()
      .setMatchedStrings(List.of( "245", "300", "888", "4444"))
      .setExcludedStrings(List.of("a", "aaa"))
      .setRequiredSubStrings(List.of())
      .setExcludedAnswers(List.of("\\d{1,}"))
      .setAnswers(List.of("[0-9]*"));

    return regExpTask;
  }


  public static RegExpTask getOneBinary() {
    var regExpTask = of()
      .setMatchedStrings(List.of("0", "1"))
      .setExcludedStrings(List.of("a", "11", "2", "3"))
      .setRequiredSubStrings(List.of())
      .setExcludedAnswers(List.of("\\d", "\\d{0,1}", "\\d{1,1}"))
      .setAnswers(List.of("[0,1]", "[0-1]"));
    return regExpTask;
  }

  public static RegExpTask getThreeBinary() {
    var regExpTask = of()
      .setMatchedStrings(List.of("000", "111", "001"))
      .setExcludedStrings(List.of("a", "abc", "1234", "12", "00", "1", "1111", "222"))
      .setRequiredSubStrings(List.of())
      .setExcludedAnswers(List.of("\\d{3}", "\\d{3,3}"))
      .setAnswers(List.of("[0,1]{3}", "[0-1]{3}"));
    return regExpTask;
  }


  public static RegExpTask getMultipleBinary() {
    var regExpTask = of()
      .setMatchedStrings(List.of( "0000", "1", "10", "001"))
      .setExcludedStrings(List.of("a", "aaa", "5", "22"))
      .setRequiredSubStrings(List.of())
      .setExcludedAnswers(List.of("\\d{1,}"))
      .setAnswers(List.of("[0,1]*", "[0-1]*"));

    return regExpTask;
  }

  public static RegExpTask getOneRangeWithPoint() {
    var regExpTask = of()
      .setMatchedStrings(List.of("0", "1", "2", "3", "5"))
      .setExcludedStrings(List.of("a", "11", "4", "6", "7", "8","9"))
      .setExcludedAnswers(List.of("[0,1,2,3,5]"))
      .setAnswers(List.of( "[0-3,5]"));
    return regExpTask;
  }

  public static RegExpTask getThreeRangeWithPoint() {
    var regExpTask = of()
      .setMatchedStrings(List.of("000", "111", "125"))
      .setExcludedStrings(List.of("a", "abc", "1234", "12", "124", "444", "1111", "666", "777", "888", "999"))
      .setExcludedAnswers(List.of("[0,1,2,3,5]{3}"))
      .setAnswers(List.of("[0-3,5]{3}"));
    return regExpTask;
  }


  public static RegExpTask getMultipleRangeWithPoint() {
    var regExpTask = of()
      .setMatchedStrings(List.of( "0000", "5555", "10", "001"))
      .setExcludedStrings(List.of("a", "aaa", "4", "6", "7", "8", "9"))
      .setExcludedAnswers(List.of("[0,1,2,3,5]*"))
      .setAnswers(List.of("[0-3,5]*"));
    return regExpTask;
  }




}
