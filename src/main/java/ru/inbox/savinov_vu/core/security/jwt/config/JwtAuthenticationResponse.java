package ru.inbox.savinov_vu.core.security.jwt.config;

import lombok.AllArgsConstructor;
import ru.inbox.savinov_vu.app.users.model.Authority;
import ru.inbox.savinov_vu.core.security.jwt.model.SecurityUser;

import java.io.Serializable;
import java.util.Set;



@AllArgsConstructor(staticName = "of")
public class JwtAuthenticationResponse implements Serializable {

  private static final long serialVersionUID = 1250166508152483573L;

  private final String token;

  private final Integer id;

  private final String fullName;

  private final Set<Authority> roles;


  public String getToken() {
    return this.token;
  }


  public Integer getId() {
    return id;
  }


  public String getFullName() {
    return fullName;
  }


  public Set<Authority> getRoles() {
    return roles;
  }


  public static JwtAuthenticationResponse of(SecurityUser securityUser, String token) {
    return JwtAuthenticationResponse.of(token, securityUser.getId(), securityUser.getFullName(), securityUser.getAuthorities());
  }
}
