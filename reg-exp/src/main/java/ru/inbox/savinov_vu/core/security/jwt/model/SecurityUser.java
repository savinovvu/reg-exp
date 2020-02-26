package ru.inbox.savinov_vu.core.security.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.userdetails.UserDetails;
import ru.inbox.savinov_vu.app.users.model.Authority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.Set;



// todo: split UserDetails and SecurityUser
@Entity
@Table(name = "users")
@Getter
@AllArgsConstructor
public class SecurityUser implements UserDetails {

  @Id
  private Integer id;

  private String login;

  private String password;

  private Boolean enabled;

  @CreatedDate
  private Date lastPasswordResetDate;


  public SecurityUser() {
  }


  public SecurityUser(Integer id) {
    this.id = id;
  }


  @Override
  @Transient
  public String getUsername() {
    return login;
  }

  @Transient
  public Set<Authority> getAuthorities() {
    return Set.of(Authority.ADMIN, Authority.USER);
  }

  @Override
  public String getPassword() {
    return password;
  }


  @Override
  @Transient
  public boolean isAccountNonExpired() {
    return true;
  }


  @Override
  @Transient
  public boolean isAccountNonLocked() {
    return true;
  }


  @Override
  @Transient
  public boolean isCredentialsNonExpired() {
    return true;
  }


  @Override
  public boolean isEnabled() {
    return enabled;
  }


}