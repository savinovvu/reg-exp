package ru.inbox.savinov_vu.core.security.jwt.config;

import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.crypto.SecretKey;
import java.nio.charset.Charset;



@ConfigurationProperties("jwt")
@ConstructorBinding
@Getter
public class JwtParams {

  private final String header;

  private final String secret;

  private final int expiration;

  private final SecretKey secretKey;


  public JwtParams(String header, String secret, int expiration) {
    this.header = header;
    this.secret = secret;
    this.expiration = expiration;
    byte[] bytes = secret.getBytes(Charset.forName("UTF-8"));
    secretKey = Keys.hmacShaKeyFor(bytes);

  }
}
