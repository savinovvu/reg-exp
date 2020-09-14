package ru.inbox.savinov_vu.test_helpers.mock;

import org.mockito.Mockito;
import ru.inbox.savinov_vu.app.users.repository.UserRepository;
import ru.inbox.savinov_vu.app.users.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;
import static ru.inbox.savinov_vu.test_helpers.data.factories.RegExpLevelFactory.getRegExpLevelWithId;
import static ru.inbox.savinov_vu.test_helpers.data.factories.regexpTask.RegExpTaskFactory.getRegExpTaskWithId;
import static ru.inbox.savinov_vu.test_helpers.data.factories.user.UserFactory.getOne;
import static ru.inbox.savinov_vu.test_helpers.data.factories.user.UserFactory.getWithId;



public class UserMock {

  public static UserRepository getUserRepositoryMock() {
    UserRepository mock = Mockito.mock(UserRepository.class);
    when(mock.findSolvedLevels(1)).thenReturn(Set.of(getRegExpLevelWithId(2)));
    when(mock.saveAndFlush(getOne())).thenReturn(getWithId(1));
    when(mock.findAll()).thenReturn(List.of(getWithId(1), getWithId(2)));
    when(mock.findById(1)).thenReturn(Optional.of(getWithId(1)));
    when(mock.getByLogin("login")).thenReturn(getWithId(1));
    when(mock.existsByLogin("login")).thenReturn(true);
    when(mock.findSolvedTasks(1)).thenReturn(Set.of(getRegExpTaskWithId(1)), Set.of(getRegExpTaskWithId(2)));
    return mock;
  }


  public static UserService getUserServiceMock() {
    UserService mock = Mockito.mock(UserService.class);
    when(mock.getAll()).thenReturn(List.of(getWithId(1), getWithId(2)));
    when(mock.getByLogin("login")).thenReturn(getWithId(1));
    when(mock.delete(1)).thenReturn(true);
    when(mock.getByLogin("admin")).thenReturn(getWithId(1));
    return mock;
  }

}
