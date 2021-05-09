package ru.inbox.savinov_vu.app.tasks.level.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import ru.inbox.savinov_vu.app.tasks.level.dto.RegExpLevelDto;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel;
import ru.inbox.savinov_vu.app.tasks.level.model.RegExpLevel_;
import ru.inbox.savinov_vu.app.tasks.level.repository.RegExpLevelRepository;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.service.UserService;
import ru.inbox.savinov_vu.test_helpers.data.factories.RegExpLevelFactory;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static ru.inbox.savinov_vu.test_helpers.data.factories.RegExpLevelFactory.getRegExpLevelsWithNumbers;
import static ru.inbox.savinov_vu.test_helpers.data.factories.user.UserFactory.getWithId;

@ExtendWith(MockitoExtension.class)
class RegExpLevelServiceTest {


  private RegExpLevelService subject;

  @Mock
  private RegExpLevelRepository repository;

  @Mock
  private UserService userService;


  @BeforeEach
  void setUp() {
    subject = new RegExpLevelService(repository, userService);
  }

  @Test
  public void findById_when_success_search() {
    int id = 1;
    RegExpLevel expected = RegExpLevelFactory.getRegExpLevelWithId(id);
    when(repository.findById(1)).thenReturn(Optional.of(expected));
    RegExpLevel actual = subject.findById(1);
    assertEquals(expected, actual);

    verify(repository).findById(id);
    verifyNoMoreInteractions(repository);
    verifyNoInteractions(userService);
  }

  @Test
  public void findById_when_null_search() {
    int id = 1;
    RegExpLevel actual = subject.findById(1);
    assertThat(actual).isNull();

    verify(repository).findById(id);
    verifyNoMoreInteractions(repository);
    verifyNoInteractions(userService);
  }

  @Test
  public void findAllForUser_success_search() {
    int userId = 1;
    User user = getWithId(userId);
    when(userService.getById(user.getId())).thenReturn(user);

    List<RegExpLevel> regExpLevels = getRegExpLevelsWithNumbers(1, 2, 3);
    regExpLevels.get(0).setUsers(List.of(user));
    regExpLevels.get(1).setScore(20);
    regExpLevels.get(2).setId(3).setEnDescription("descr");
    when(repository.findAll(Sort.by(Sort.Direction.ASC, RegExpLevel_.NUMBER))).thenReturn(regExpLevels);

    List<RegExpLevelDto> actual = subject.findAllForUser(userId);
    assertTrue(actual.get(0).isSolve());
    assertFalse(actual.get(1).isSolve());
    assertFalse(actual.get(2).isSolve());
    assertEquals(actual.get(0).getDescription(), "some description");
    assertEquals(actual.get(2).getDescription(), "descr");
    assertEquals(actual.get(0).getScore(), 0);
    assertEquals(actual.get(1).getScore(), 20);

    verify(userService).getById(userId);
    verify(repository).findAll(Sort.by(Sort.Direction.ASC, RegExpLevel_.NUMBER));
  }


}
