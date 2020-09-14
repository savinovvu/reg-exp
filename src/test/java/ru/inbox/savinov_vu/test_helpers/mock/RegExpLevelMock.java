package ru.inbox.savinov_vu.test_helpers.mock;

import org.mockito.Mockito;
import org.springframework.data.domain.Sort;
import ru.inbox.savinov_vu.app.tasks.level.repository.RegExpLevelRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static ru.inbox.savinov_vu.test_helpers.data.factories.RegExpLevelFactory.*;



public class RegExpLevelMock {

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



}
