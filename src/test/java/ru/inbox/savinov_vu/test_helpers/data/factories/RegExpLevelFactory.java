package ru.inbox.savinov_vu.test_helpers.data.factories;

import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class RegExpLevelFactory {


  public static RegExpLevel getRegExpLevel() {
    RegExpLevel regExpLevel = new RegExpLevel()
      .setNumber(1)
      .setEnDescription("some description")
      .setRuDescription("описание")
      .setRegExpTasks(List.of())
      .setUsers(List.of())
      .setEnabled(true);
    return regExpLevel;
  }


  public static RegExpLevel getRegExpLevelWithId(Integer id) {
    RegExpLevel regExpLevel = getRegExpLevel()
      .setId(id);
    return regExpLevel;
  }


  public static RegExpLevel getRegExpLevelWithNumber(Integer number) {
    RegExpLevel regExpLevel = getRegExpLevel()
      .setId(1)
      .setNumber(number);
    return regExpLevel;
  }

  public static List<RegExpLevel> getRegExpLevelsWithNumbers(Integer... numbers) {
    return Arrays.stream(numbers).map(RegExpLevelFactory::getRegExpLevelWithNumber).collect(Collectors.toList());
  }


}
