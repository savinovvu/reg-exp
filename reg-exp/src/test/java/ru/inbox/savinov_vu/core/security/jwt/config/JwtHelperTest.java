package ru.inbox.savinov_vu.core.security.jwt.config;

import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.inbox.savinov_vu.core.security.jwt.model.SecurityUser;
import ru.inbox.savinov_vu.testhelpers.factories.jwt.JwtParamsFactory;
import ru.inbox.savinov_vu.testhelpers.factories.jwt.SecurityUserFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



class JwtHelperTest {

  private static final String VALID_SECRET = "ZUSb5prVYqrnR2HjXIQ614s4Ac+2kNBlhpMyGBEr31I=";


  @Test
  void getUserName_validToken() {

    JwtHelper helper = new JwtHelper(JwtParamsFactory.of());
    SecurityUser securityUser = SecurityUserFactory.of();
    String token = helper.generateToken(securityUser);

    String actual = helper.getUserName(token);
    assertEquals(securityUser.getLogin(), actual);
  }

  @Test
  void getUserName_tokenExpired_throwsException() throws Exception {
    JwtHelper helper = new JwtHelper(JwtParamsFactory.byExpiration(0));
    SecurityUser user = SecurityUserFactory.of();
    String token = helper.generateToken(user);
    Thread.sleep(10);
    assertThrows(ExpiredJwtException.class, () -> helper.validateToken(token, user));
  }


  @ParameterizedTest
  @ValueSource(strings = {"", " "})
  @NullSource
  void generateToken_invalidUserName(String userName) {
    JwtHelper helper = new JwtHelper(JwtParamsFactory.of());
    assertThrows(IllegalArgumentException.class, () -> helper.generateToken(SecurityUserFactory.byName(userName)));
  }





}
