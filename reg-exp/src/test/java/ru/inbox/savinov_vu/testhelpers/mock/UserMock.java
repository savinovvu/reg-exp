package ru.inbox.savinov_vu.testhelpers.mock;

import org.mockito.Mockito;
import ru.inbox.savinov_vu.app.users.repository.UserRepository;
import ru.inbox.savinov_vu.app.users.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;
import static ru.inbox.savinov_vu.testhelpers.data.factories.RegExpLevelFactory.getRegExpLevelWithId;
import static ru.inbox.savinov_vu.testhelpers.data.factories.RegExpTaskFactory.getRegExpTaskWithId;
import static ru.inbox.savinov_vu.testhelpers.data.factories.UserFactory.getUser;
import static ru.inbox.savinov_vu.testhelpers.data.factories.UserFactory.getUserWithId;



public class UserMock {

  public static UserRepository getUserRepositoryMock() {
    UserRepository mock = Mockito.mock(UserRepository.class);
    when(mock.findSolvedLevels(1)).thenReturn(Set.of(getRegExpLevelWithId(2)));
    when(mock.saveAndFlush(getUser())).thenReturn(getUserWithId(1));
    when(mock.findAll()).thenReturn(List.of(getUserWithId(1), getUserWithId(2)));
    when(mock.findById(1)).thenReturn(Optional.of(getUserWithId(1)));
    when(mock.getByLogin("login")).thenReturn(getUserWithId(1));
    when(mock.existsByLogin("login")).thenReturn(true);
    when(mock.findSolvedTasks(1)).thenReturn(Set.of(getRegExpTaskWithId(1)), Set.of(getRegExpTaskWithId(2)));
    return mock;
  }


  public static UserService getUserServiceMock() {
    UserService mock = Mockito.mock(UserService.class);
    when(mock.findSolvedLevels(1)).thenReturn(Set.of(getRegExpLevelWithId(2)));
    when(mock.add(getUser())).thenReturn(getUserWithId(1));
    when(mock.getAll()).thenReturn(List.of(getUserWithId(1), getUserWithId(2)));
    when(mock.getByLogin("login")).thenReturn(getUserWithId(1));
    when(mock.getById(1)).thenReturn(getUserWithId(1));
    when(mock.delete(1)).thenReturn(true);
    when(mock.getByLogin("admin")).thenReturn(getUserWithId(1));
    return mock;
  }

}
