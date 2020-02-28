package ru.inbox.savinov_vu.testhelpers.data.factories.user;

import ru.inbox.savinov_vu.app.users.model.Sex;
import ru.inbox.savinov_vu.app.users.model.User;

import java.util.ArrayList;
import java.util.List;

import static ru.inbox.savinov_vu.testhelpers.data.factories.constant.Constants.LocalDateConstant.START_UNIX_EPOCH_LOCAL_DATE;
import static ru.inbox.savinov_vu.testhelpers.data.factories.constant.Constants.LocalDateConstant.START_XX_AGE_LOCALDATE;
import static ru.inbox.savinov_vu.testhelpers.data.factories.constant.Constants.LocalDateTimeConstant.START_UNIX_EPOCH_DATE_TIME;



public class UserFactory {


  public static User getOne() {
    User user = buildUser("");
    return user;
  }


  public static List<User> getUserList(int size) {
    var users = new ArrayList<User>();
    for (int i = 0; i < size; i++) {
      users.add(buildUser(String.valueOf(i)));
    }
    return users;
  }


  public static List<User> getDifferentUserList() {
    List<User> userList = getUserList(30);
    User mixedUser = userList.get(10);
    mixedUser.setFirstName("firstName1");
    mixedUser.setLastName("lastName2");
    mixedUser.setEnabled(true);
    mixedUser.setSex(Sex.WOMAN);
    mixedUser.setBirthDate(START_XX_AGE_LOCALDATE);

    User sortedFirstNameUser = userList.get(11);
    sortedFirstNameUser.setFirstName("aaaaa");

    User sortedLastNameUser = userList.get(12);
    sortedFirstNameUser.setLastName("aaaaa");

    User disabledUser = userList.get(13);
    disabledUser.setEnabled(false);

    userList.addAll(List.of(getOne(), getDisabled(), getWoman(), getOld()));
    return userList;
  }


  public static User getWithId(Integer id) {
    User user = getOne()
      .setId(id);
    return user;
  }


  public static User getDisabled() {
    User user = buildUser("disabled");
    user.setEnabled(false);
    return user;
  }


  public static User getWoman() {
    User user = buildUser("woman");
    user.setSex(Sex.WOMAN);
    return user;
  }


  public static User getOld() {
    User user = buildUser("old");
    user.setBirthDate(START_XX_AGE_LOCALDATE);
    return user;
  }


  private static User buildUser(String s) {
    return new User()
      .setFirstName("firstName" + s)
      .setLastName("lastName" + s)
      .setLogin("login" + s)
      .setPassword("password")
      .setLastPasswordResetDate(START_UNIX_EPOCH_DATE_TIME)
      .setEnabled(true)
      .setEmail("email" + s + "@email.com")
      .setSex(Sex.MAN)
      .setBirthDate(START_UNIX_EPOCH_LOCAL_DATE);
  }


}
