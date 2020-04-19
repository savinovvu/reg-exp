package ru.inbox.savinov_vu.test_helpers.data.factories.jwt;

import ru.inbox.savinov_vu.core.security.jwt.config.JwtHelper;
import ru.inbox.savinov_vu.core.security.jwt.config.JwtParams;



public class JwtHelperFactory {

  public static JwtHelper of() {
    JwtParams jwtParams = JwtParamsFactory.of();
    JwtHelper helper = new JwtHelper(jwtParams);
    return helper;
  }


  public static JwtHelper of(JwtParams jwtParams) {
    JwtHelper helper = new JwtHelper(jwtParams);
    return helper;
  }

}
