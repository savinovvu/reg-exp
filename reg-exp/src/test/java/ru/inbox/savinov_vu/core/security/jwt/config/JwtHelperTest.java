package ru.inbox.savinov_vu.core.security.jwt.config;

import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.inbox.savinov_vu.common.util.DateTimeUtils;
import ru.inbox.savinov_vu.config.AbstractJunitTest;
import ru.inbox.savinov_vu.core.security.jwt.model.SecurityUser;
import ru.inbox.savinov_vu.test_helpers.data.factories.jwt.JwtHelperFactory;
import ru.inbox.savinov_vu.test_helpers.data.factories.jwt.JwtParamsFactory;
import ru.inbox.savinov_vu.test_helpers.data.factories.jwt.SecurityUserFactory;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.inbox.savinov_vu.test_helpers.data.factories.constant.Constants.LOGIN;
import static ru.inbox.savinov_vu.test_helpers.data.factories.jwt.JwtTokenFactory.getTokenByCreateTime;



class JwtHelperTest extends AbstractJunitTest {


  @Test
  void getUserName_validToken() {

    JwtHelper helper = JwtHelperFactory.of();
    String token = helper.generateToken(LOGIN);
    String actual = helper.getUserName(token);
    assertEquals(LOGIN, actual);
  }


  @Test
  void getUserName_tokenExpired_throwsException() throws Exception {
    JwtHelper helper = JwtHelperFactory.of(JwtParamsFactory.byExpiration(0));
    SecurityUser user = SecurityUserFactory.of();
    String token = helper.generateToken(user.getLogin());
    Thread.sleep(10);
    assertThrows(ExpiredJwtException.class, () -> helper.validateToken(token, user));
  }


  @ParameterizedTest
  @ValueSource(strings = {"", " "})
  @NullSource
  void generateToken_invalidUserName(String userName) {
    JwtHelper helper = JwtHelperFactory.of();
    assertThrows(IllegalArgumentException.class, () -> helper.generateToken(userName));
  }


  @ParameterizedTest
  @ValueSource(strings = {"", "test"})
  @NullSource
  void getUserName_invalidToken_throwException(String token) {
    JwtHelper helper = JwtHelperFactory.of();
    assertThrows(Exception.class, () -> helper.getUserName(token));
  }


  @Test
  @DisplayName("token will not be refresh")
  void canTokenBeRefreshed() {
    String token = getTokenByCreateTime(LocalDateTime.now());
    Date lastPasswordReset = DateTimeUtils.convertLocalDateTimeToDate(LocalDateTime.now().plusDays(1));
    JwtHelper jwtHelper = JwtHelperFactory.of();
    Boolean result = jwtHelper.canTokenBeRefreshed(token, lastPasswordReset);
    assertFalse(result);
  }


}
