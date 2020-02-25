package ru.inbox.savinov_vu.core.security.jwt.config;

import io.jsonwebtoken.security.WeakKeyException;
import org.junit.jupiter.api.Test;
import ru.inbox.savinov_vu.testhelpers.factories.jwt.JwtParamsFactory;

import static org.junit.jupiter.api.Assertions.*;



class JwtParamsTest {

  @Test
  void invalidSecretThrowException() {
    assertThrows(WeakKeyException.class, JwtParamsFactory::getInvalidSecretJwtParams);
  }
}
