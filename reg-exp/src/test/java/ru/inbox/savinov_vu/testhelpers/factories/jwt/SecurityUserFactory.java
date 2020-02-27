package ru.inbox.savinov_vu.testhelpers.factories.jwt;

import ru.inbox.savinov_vu.core.security.jwt.model.SecurityUser;

import java.util.Date;



public class SecurityUserFactory {

  public static SecurityUser of() {
    return new SecurityUser(1, "login", "password", true, "fullName", new Date(100));
  }


  public static SecurityUser byName(String name) {
    return new SecurityUser(1, name, "password", true, "fullName", new Date(100));
  }
}
