package ru.inbox.savinov_vu.stumb;

import org.mockito.Mockito;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.repository.UserRepository;
import ru.inbox.savinov_vu.app.users.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;
import static ru.inbox.savinov_vu.stumb.RegExpLevelFactory.getRegExpLevelWithId;
import static ru.inbox.savinov_vu.stumb.RegExpTaskFactory.getRegExpTaskWithId;



public class UserFactory {

  public static User getUser() {
    User user = new User()
      .setName("name")
      .setLogin("login")
      .setPassword("password")
      .setEmail("email@email.com")
      .setEnabled(true);
    return user;
  }


  public static User getUserWithId(Integer id) {
    User user = getUser()
      .setId(id);
    return user;
  }


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
    return mock;
  }

}
