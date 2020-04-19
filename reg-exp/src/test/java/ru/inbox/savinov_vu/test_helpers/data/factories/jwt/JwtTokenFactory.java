package ru.inbox.savinov_vu.test_helpers.data.factories.jwt;

import io.jsonwebtoken.Jwts;
import ru.inbox.savinov_vu.common.util.DateTimeUtils;
import ru.inbox.savinov_vu.core.security.jwt.config.JwtParams;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

import static ru.inbox.savinov_vu.core.security.jwt.config.JwtHelper.JWT_ALGORITHM;
import static ru.inbox.savinov_vu.test_helpers.data.factories.constant.Constants.LOGIN;



public class JwtTokenFactory {

  public static String getToken(String login, LocalDateTime createdDateTime, JwtParams jwtParams) {
    Date createdDate = DateTimeUtils.convertLocalDateTimeToDate(createdDateTime);
    Date expirationDate = getExpirationDate(createdDate, jwtParams);

    return Jwts.builder()
      .setClaims(new HashMap<>())
      .setSubject(login)
      .setIssuedAt(createdDate)
      .setExpiration(expirationDate)
      .signWith(jwtParams.getSecretKey(), JWT_ALGORITHM)
      .compact();
  }


  private static Date getExpirationDate(Date createdDate, JwtParams jwtParams) {
    return new Date(createdDate.getTime() + jwtParams.getExpiration() * 1000);
  }


  public static String getExpiredToken() {
    JwtParams jwtParams = JwtParamsFactory.byExpiration(-100000);
    return getToken(LOGIN, LocalDateTime.now().plusDays(1), jwtParams);
  }


  public static String getValidToken() {
    JwtParams jwtParams = JwtParamsFactory.of();
    return getToken(LOGIN, LocalDateTime.now(), jwtParams);
  }


  public static String getTokenByCreateTime(LocalDateTime localDateTime) {
    JwtParams jwtParams = JwtParamsFactory.of();
    return getToken(LOGIN, localDateTime, jwtParams);
  }

}
