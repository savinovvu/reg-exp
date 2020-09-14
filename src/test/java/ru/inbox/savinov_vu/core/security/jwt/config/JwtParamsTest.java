package ru.inbox.savinov_vu.core.security.jwt.config;

import io.jsonwebtoken.security.WeakKeyException;
import org.junit.jupiter.api.Test;
import ru.inbox.savinov_vu.config.AbstractJunitTest;
import ru.inbox.savinov_vu.test_helpers.data.factories.jwt.JwtParamsFactory;

import static org.junit.jupiter.api.Assertions.*;



class JwtParamsTest extends AbstractJunitTest {

  @Test
  void invalidSecretThrowException() {
    assertThrows(WeakKeyException.class, JwtParamsFactory::getInvalidSecretJwtParams);
  }
}
