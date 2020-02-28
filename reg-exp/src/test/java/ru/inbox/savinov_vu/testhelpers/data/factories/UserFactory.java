package ru.inbox.savinov_vu.testhelpers.data.factories;

import ru.inbox.savinov_vu.app.users.model.User;



public class UserFactory {


  public static User getUser() {
    User user = new User()
      .setFirstName("firstName")
      .setLastName("lastName")
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




}
