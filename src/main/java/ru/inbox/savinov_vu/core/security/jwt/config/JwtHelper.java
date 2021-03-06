package ru.inbox.savinov_vu.core.security.jwt.config;


import com.google.common.base.Preconditions;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.inbox.savinov_vu.core.security.jwt.model.SecurityUser;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static java.util.Objects.isNull;



@Slf4j
@Component
public class JwtHelper implements Serializable {

  private static final long serialVersionUID = -3301605591108950415L;

  public static final SignatureAlgorithm JWT_ALGORITHM = SignatureAlgorithm.HS256;

  private static final String CLAIM_KEY_USERNAME = "sub";

  private static final String CLAIM_KEY_CREATED = "iat";


  private Clock clock;

  private JwtParams jwtParams;

  private JwtParser jwtParser;


  public JwtHelper(JwtParams jwtParams) {
    this.clock = DefaultClock.INSTANCE;
    this.jwtParams = jwtParams;
    this.jwtParser = Jwts.parserBuilder()
      .setSigningKey(jwtParams.getSecretKey())
      .build();
  }


  public String getUserNameOrNull(String authToken) {
    if (isNull(authToken)) {
      return null;
    }
    try {
      return getUserName(authToken);
    } catch (IllegalArgumentException e) {
      LOG.error("an error occured during getting username from token", e);
      return null;
    } catch (ExpiredJwtException e) {
      LOG.warn("the token is expired and not valid anymore", e);
      return null;
    }
  }


  public String getUserName(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }


  public Date getIssuedAtDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getIssuedAt);
  }


  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }


  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }


  private Claims getAllClaimsFromToken(String token) {
    return (Claims) jwtParser.parse(token).getBody();
  }


  private Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(clock.now());
  }


  private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
    return (lastPasswordReset != null && created.before(lastPasswordReset));
  }


  private Boolean ignoreTokenExpiration(String token) {
    // here you specify tokens, for that the expiration is ignored
    return false;
  }


  public String generateToken(String login) {
    Preconditions.checkArgument(!Strings.isBlank(login), "User name can't be blank");
    Map<String, Object> claims = new HashMap<>();
    return doGenerateToken(claims, login);
  }


  private String doGenerateToken(Map<String, Object> claims, String subject) {
    final Date createdDate = clock.now();
    final Date expirationDate = calculateExpirationDate(createdDate);

    return Jwts.builder()
      .setClaims(claims)
      .setSubject(subject)
      .setIssuedAt(createdDate)
      .setExpiration(expirationDate)
      .signWith(jwtParams.getSecretKey(), JWT_ALGORITHM)
      .compact();
  }


  public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
    final Date created = getIssuedAtDateFromToken(token);
    return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
      && (!isTokenExpired(token) || ignoreTokenExpiration(token));
  }


  public String refreshToken(String token) {
    final Date createdDate = clock.now();
    final Date expirationDate = calculateExpirationDate(createdDate);

    final Claims claims = getAllClaimsFromToken(token);
    claims.setIssuedAt(createdDate);
    claims.setExpiration(expirationDate);

    return Jwts.builder()
      .setClaims(claims)
      .signWith(jwtParams.getSecretKey(), JWT_ALGORITHM)
      .compact();
  }


  public boolean validateToken(String token, UserDetails userDetails) {
    SecurityUser securityUser = (SecurityUser) userDetails;
    final String username = getUserName(token);
    final Date created = getIssuedAtDateFromToken(token);
    final Date expiration = getExpirationDateFromToken(token);
    return (
      username.equals(securityUser.getUsername())
        && !isTokenExpired(token)
        && !isCreatedBeforeLastPasswordReset(created, securityUser.getLastPasswordResetDate())
    );
  }


  private Date calculateExpirationDate(Date createdDate) {
    return new Date(createdDate.getTime() + jwtParams.getExpiration() * 1000);
  }


  public String getAuthToken(HttpServletRequest request) {
    final String requestHeader = request.getHeader(jwtParams.getHeader());
    String authToken = getAuthToken(requestHeader);
    return authToken;
  }


  private String getAuthToken(String requestHeader) {
    if (isNull(requestHeader) || !requestHeader.startsWith("Bearer ")) {
      LOG.warn("couldn't find bearer string, will ignore the header");
      return null;
    }
    String authToken = requestHeader.substring(7);
    return authToken;
  }


}

