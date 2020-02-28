package ru.inbox.savinov_vu.app.users.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.inbox.savinov_vu.app.users.dto.UserDto;
import ru.inbox.savinov_vu.app.users.model.Sex;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.app.users.model.User_;
import ru.inbox.savinov_vu.app.users.repository.UserFilter;
import ru.inbox.savinov_vu.common.paged.PagedResultList;
import ru.inbox.savinov_vu.config.AbstractSpringBootTest;
import ru.inbox.savinov_vu.testhelpers.data.factories.user.UserFilterDtoFactory;
import ru.inbox.savinov_vu.testhelpers.data.init.UserInitializer;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.inbox.savinov_vu.testhelpers.data.factories.constant.Constants.LocalDateConstant.GAGARINS_FLY_DAY_LOCAL_DATE;
import static ru.inbox.savinov_vu.testhelpers.data.factories.user.UserFactory.getDifferentUserList;
import static ru.inbox.savinov_vu.testhelpers.data.factories.user.UserFilterDtoFactory.compositeFilter;
import static ru.inbox.savinov_vu.testhelpers.data.factories.user.UserFilterDtoFactory.getByBirthDate;
import static ru.inbox.savinov_vu.testhelpers.data.factories.user.UserFilterDtoFactory.getByEmail;
import static ru.inbox.savinov_vu.testhelpers.data.factories.user.UserFilterDtoFactory.getByFirstName;
import static ru.inbox.savinov_vu.testhelpers.data.factories.user.UserFilterDtoFactory.getByLastName;
import static ru.inbox.savinov_vu.testhelpers.data.factories.user.UserFilterDtoFactory.getBySex;
import static ru.inbox.savinov_vu.testhelpers.data.factories.user.UserFilterDtoFactory.getDisabled;
import static ru.inbox.savinov_vu.testhelpers.data.factories.user.UserFilterDtoFactory.getEnabled;
import static ru.inbox.savinov_vu.testhelpers.data.factories.user.UserFilterDtoFactory.getFive;
import static ru.inbox.savinov_vu.testhelpers.data.factories.user.UserFilterDtoFactory.getSortedByBirthDateAsc;
import static ru.inbox.savinov_vu.testhelpers.data.factories.user.UserFilterDtoFactory.getSortedByEmailAsc;
import static ru.inbox.savinov_vu.testhelpers.data.factories.user.UserFilterDtoFactory.getSortedByFirstNameAsc;
import static ru.inbox.savinov_vu.testhelpers.data.factories.user.UserFilterDtoFactory.getSortedByLastNameAsc;
import static ru.inbox.savinov_vu.testhelpers.data.factories.user.UserFilterDtoFactory.getSortedByLoginAsc;
import static ru.inbox.savinov_vu.testhelpers.data.factories.user.UserFilterDtoFactory.getSortedBySexDesc;



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
  void getListByFilter(UserFilter filter, int expectedCount, String message) {
    List<User> differentUserList = getDifferentUserList();
    userInitializer.initOneList(differentUserList);
    PagedResultList<UserDto> byFilter = userService.getByFilter(filter);
    assertEquals(expectedCount, byFilter.getItems().size(), message);
  }


  public static Stream<Arguments> getFilters() {
    return Stream.of(
      Arguments.of(UserFilter.of(getEnabled()), 32, "search by enabled users"),
      Arguments.of(UserFilter.of(getDisabled()), 2, "search by disabled users"),
      Arguments.of(UserFilter.of(getByFirstName("firstName1")), 2, "search by firstName"),
      Arguments.of(UserFilter.of(getByLastName("lastName2")), 2, "search by lastName"),
      Arguments.of(UserFilter.of(getByEmail("email1@email.com")), 1, "search by email"),
      Arguments.of(UserFilter.of(getBySex(Sex.WOMAN.getValue())), 2, "search by sex"),
      Arguments.of(UserFilter.of(getByBirthDate(GAGARINS_FLY_DAY_LOCAL_DATE)), 2, "search by birthDate"),
      Arguments.of(UserFilter.of(getFive()), 5, "get 5 items"),
      Arguments.of(UserFilter.of(compositeFilter()), 1, "composite filter")
    );
  }


  @Test
  void getListByFilterWithId() {
    List<User> differentUserList = getDifferentUserList();
    List<User> users = userInitializer.initOneList(differentUserList);
    Integer id = users.get(0).getId();
    UserFilter filter = UserFilter.of(UserFilterDtoFactory.getById(String.valueOf(id)));
    PagedResultList<UserDto> byFilter = userService.getByFilter(filter);
    assertEquals(1, byFilter.getItems().size(), "search by id must return only 1 user");
    assertEquals(String.valueOf(id), byFilter.getItems().get(0).getId(), "returned user must have searched id");
  }


  @ParameterizedTest
  @MethodSource("getSortFilters")
  void getSortedListWithFilters(UserFilter filter, String expectedResult, String fieldName, String message) {
    List<User> differentUserList = getDifferentUserList();
    userInitializer.initOneList(differentUserList);
    PagedResultList<UserDto> byFilter = userService.getByFilter(filter);
    UserDto userDto = byFilter.getItems().get(0);
    Object checkedPropertyOfSort;
    try {
      Field declaredField = userDto.getClass().getDeclaredField(fieldName);
      declaredField.setAccessible(true);
      checkedPropertyOfSort = declaredField.get(userDto);
    } catch (Exception e) {
      throw new RuntimeException("UserDto fields must have same fields like user");
    }

    assertEquals(expectedResult, String.valueOf(checkedPropertyOfSort), message);
  }


  public static Stream<Arguments> getSortFilters() {
    return Stream.of(
      Arguments.of(UserFilter.of(getSortedByFirstNameAsc()), "aaaaa", User_.FIRST_NAME, "asc by firtsName"),
      Arguments.of(UserFilter.of(getSortedByLastNameAsc()), "aaaaa", User_.LAST_NAME, "asc by lastName"),
      Arguments.of(UserFilter.of(getSortedByEmailAsc()), "aaa@email.com", User_.EMAIL, "asc by email"),
      Arguments.of(UserFilter.of(getSortedByLoginAsc()), "aaaaaa", User_.LOGIN, "asc by login"),
      Arguments.of(UserFilter.of(getSortedBySexDesc()), "WOMAN", User_.SEX, "asc by sex"),
      Arguments.of(UserFilter.of(getSortedByBirthDateAsc()), GAGARINS_FLY_DAY_LOCAL_DATE.toString(), User_.BIRTH_DATE, "asc by birthDate")
    );
  }

  @Test
  void getSecondPage(){
    List<User> differentUserList = getDifferentUserList();
    userInitializer.initOneList(differentUserList);
    UserFilter filter = UserFilter.of(UserFilterDtoFactory.getSecondPage());
    PagedResultList<UserDto> pagedResult = userService.getByFilter(filter);
    System.out.println(pagedResult);
    String firstName = pagedResult.getItems().get(0).getFirstName();
    assertEquals( 2, pagedResult.getPage(), "number of page must be 2" );
    assertEquals( "firstName5", firstName, "data must be from 2 page -> it is from firstName5 user" );
  }


  @Test
  void getSortedIdDesc() {
    List<User> differentUserList = getDifferentUserList();
    userInitializer.initOneList(differentUserList);
    UserFilter filter = UserFilter.of(UserFilterDtoFactory.getSortedIdDesc());
    PagedResultList<UserDto> byFilter = userService.getByFilter(filter);
    UserDto userDto = byFilter.getItems().get(0);
    UserDto userDto1 = byFilter.getItems().get(1);
    assertEquals( 1, userDto.getId().compareToIgnoreCase(userDto1.getId()), "order by id must be desc" );
  }



}
