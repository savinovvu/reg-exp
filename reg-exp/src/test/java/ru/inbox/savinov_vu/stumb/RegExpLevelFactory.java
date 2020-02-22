package ru.inbox.savinov_vu.stumb;

import org.mockito.Mockito;
import org.springframework.data.domain.Sort;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.app.tasks.level.repository.RegExpLevelRepository;
import ru.inbox.savinov_vu.app.tasks.level.service.RegExpLevelService;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;



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


  public static RegExpLevelRepository getRegExpLevelRepositoryMock() {
    RegExpLevelRepository mock = Mockito.mock(RegExpLevelRepository.class);
    when(mock.findAll()).thenReturn(List.of(getRegExpLevelWithId(1), getRegExpLevelWithId(2)));
    when(mock.findById(1)).thenReturn(Optional.of(getRegExpLevelWithId(1)));
    when(mock.findAll(Sort.by(Sort.Direction.ASC, "number"))).thenReturn(List.of(getRegExpLevelWithId(1), getRegExpLevelWithId(2)));
    when(mock.saveAndFlush(getRegExpLevel())).thenReturn(getRegExpLevelWithId(1));
    when(mock.saveAndFlush(getRegExpLevelWithId(1))).thenReturn(getRegExpLevelWithId(1));
    when(mock.findByNumber(1)).thenReturn(getRegExpLevelWithNumber(1));
    doNothing().when(mock).deleteById(getRegExpLevelWithId(1).getId());
    return mock;
  }


  public static RegExpLevelService getRegExpLevelServiceMock() {
    RegExpLevelService mock = Mockito.mock(RegExpLevelService.class);
    when(mock.findAll(1)).thenReturn(List.of(getRegExpLevelWithId(1), getRegExpLevelWithId(2)));
    when(mock.create(getRegExpLevel())).thenReturn(getRegExpLevelWithId(1));
    when(mock.update(getRegExpLevelWithId(1))).thenReturn(getRegExpLevelWithId(1));
    when(mock.delete(1)).thenReturn(true);
    when(mock.findById(1)).thenReturn(getRegExpLevelWithId(1));
    when(mock.findByNumber(1)).thenReturn(getRegExpLevelWithNumber(1));
    return mock;
  }


}
