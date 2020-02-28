package ru.inbox.savinov_vu.app.users.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.inbox.savinov_vu.app.users.dto.UserDto;
import ru.inbox.savinov_vu.app.users.model.Sex;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.repository.UserFilter;
import ru.inbox.savinov_vu.common.paged.PagedResultList;
import ru.inbox.savinov_vu.config.AbstractSpringBootTest;
import ru.inbox.savinov_vu.testhelpers.data.factories.user.UserFilterDtoFactory;
import ru.inbox.savinov_vu.testhelpers.data.init.UserInitializer;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.inbox.savinov_vu.testhelpers.data.factories.constant.Constants.LocalDateConstant.START_XX_AGE_LOCALDATE;
import static ru.inbox.savinov_vu.testhelpers.data.factories.user.UserFactory.getDifferentUserList;



class UserServiceTest extends AbstractSpringBootTest {

  @Resource
  private UserInitializer userInitializer;

  @Resource
  private UserService userService;


  @Test
  void getAll() {
    List<User> differentUserList = getDifferentUserList();
    userInitializer.initOneList(differentUserList);
    List<User> all = userService.getAll();
    assertEquals(differentUserList.size(), all.size());
  }


  @ParameterizedTest
  @MethodSource("getFilters")

  void getListByFilter(UserFilter filter, int answerCount, String message) {
    List<User> differentUserList = getDifferentUserList();
    userInitializer.initOneList(differentUserList);
    PagedResultList<UserDto> byFilter = userService.getByFilter(filter);
    assertEquals(answerCount, byFilter.getItems().size(), message);
  }


  public static Stream<Arguments> getFilters() {
    return Stream.of(
      Arguments.of(UserFilter.of(UserFilterDtoFactory.getEnabled()), 32, "search by enabled users"),
      Arguments.of(UserFilter.of(UserFilterDtoFactory.getDisabled()), 2, "search by disabled users"),
      Arguments.of(UserFilter.of(UserFilterDtoFactory.getByFirstName("firstName1")), 2, "search by firstName"),
      Arguments.of(UserFilter.of(UserFilterDtoFactory.getByLastName("lastName2")), 2, "search by lastName"),
      Arguments.of(UserFilter.of(UserFilterDtoFactory.getByEmail("email1@email.com")), 1, "search by email"),
      Arguments.of(UserFilter.of(UserFilterDtoFactory.getBySex(Sex.WOMAN.getValue())), 2, "search by sex"),
      Arguments.of(UserFilter.of(UserFilterDtoFactory.getByBirthDate(START_XX_AGE_LOCALDATE)), 2, "search by birthDate"),
      Arguments.of(UserFilter.of(UserFilterDtoFactory.getFive()), 5, "get 5 items"),
      Arguments.of(UserFilter.of(UserFilterDtoFactory.compositeFilter()), 1, "composite filter")
    );
  }

}
