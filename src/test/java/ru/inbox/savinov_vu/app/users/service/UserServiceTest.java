package ru.inbox.savinov_vu.app.users.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.inbox.savinov_vu.app.users.dto.UserDto;
import ru.inbox.savinov_vu.app.users.dto.UserFilterDto;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.repository.UserFilter;
import ru.inbox.savinov_vu.app.users.repository.UserRepository;
import ru.inbox.savinov_vu.common.paged.PagedResultList;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static ru.inbox.savinov_vu.test_helpers.data.factories.user.UserFactory.getOne;
import static ru.inbox.savinov_vu.test_helpers.data.factories.user.UserFactory.getUserList;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  private UserService subject;


  @BeforeEach
  void setUp() {
    subject = new UserService(userRepository);
  }


  @Test
  void getAll__success() {
    int resultSize = 5;
    List<User> users = getUserList(resultSize);
    given(userRepository.findAll()).willReturn(users);
    List<User> actual = subject.getAll();
    assertThat(actual)
      .hasSize(resultSize)
      .isEqualTo(users);
    verify(userRepository).findAll();
  }


  @Test
  void getByLogin__success() {
    String login = "login";
    User user = getOne();
    given(userRepository.findByLogin(login)).willReturn(user);
    User actual = subject.getByLogin(login);
    assertThat(actual).isEqualTo(user);
    verify(userRepository).findByLogin(login);
  }

  @Test
  void getByLogin__not_found() {
    String login = "login";
    User actual = subject.getByLogin(login);
    assertThat(actual).isNull();
    verify(userRepository).findByLogin(login);
  }


  @Test
  void getById__success() {
    Integer id = 5;
    User user = getOne();
    given(userRepository.findById(id)).willReturn(Optional.of(user));
    User actual = subject.getById(id);
    assertThat(actual).isEqualTo(user);
    verify(userRepository).findById(id);
  }

  @Test
  void getById__not_found() {
    Integer id = 5;
    User actual = subject.getById(id);
    assertThat(actual).isNull();
    verify(userRepository).findById(id);
  }

  @Test
  void getByFilter__success() {
    UserFilter filter = UserFilter.of(new UserFilterDto()
      .setPage(5)
      .setSize(50)
      .setSort(User.Fields.score)
      .setDirection("asc")
    );

    List<User> userList = getUserList(5);
    given(userRepository.findAll(eq(filter), ArgumentMatchers.<Pageable>any())).willReturn(new PageImpl<>(userList));
    PagedResultList<UserDto> byFilter = subject.getByFilter(filter);
    assertThat(byFilter.getSize()).isEqualTo(5);
    assertThat(byFilter.getPage()).isEqualTo(1);
    assertThat(byFilter.getTotal()).isEqualTo(5);
    assertThat(byFilter.getTotalPages()).isEqualTo(1);
    assertThat(byFilter.getItems())
      .hasSize(5);

    ArgumentCaptor<PageRequest> captor = ArgumentCaptor.forClass(PageRequest.class);
    verify(userRepository).findAll(eq(filter), captor.capture());
    List<PageRequest> allValues = captor.getAllValues();
    PageRequest pageRequest = allValues.get(0);
    assertThat(pageRequest.getPageNumber()).isEqualTo(4);
    assertThat(pageRequest.getPageSize()).isEqualTo(50);
    assertThat(pageRequest.getSort()).isEqualTo(filter.getSort());
  }



}
