package ru.inbox.savinov_vu.testhelpers.data.factories.jwt;

import ru.inbox.savinov_vu.core.security.jwt.config.JwtParams;



public class JwtParamsFactory {

  private static final String AuthorizationHeader = "Authorization";

  private static final int expiration = 31536000;

  private static final String SECRET = "ZUSb5prVYqrnR2HjXIQ614s4Ac+2kNBlhpMyGBEr31I=";


  public static JwtParams getInvalidSecretJwtParams() {
    String invalidSecret = "secret";
    return new JwtParams(AuthorizationHeader, invalidSecret, expiration);
  }


  public static JwtParams of() {
    return new JwtParams(AuthorizationHeader, SECRET, expiration);
  }

  public static JwtParams byExpiration(int expiration) {
    return new JwtParams(AuthorizationHeader, SECRET, expiration);
  }


}


