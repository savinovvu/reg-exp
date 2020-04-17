package ru.inbox.savinov_vu.core.security.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.userdetails.UserDetails;
import ru.inbox.savinov_vu.app.users.model.Authority;
import ru.inbox.savinov_vu.app.users.model.User;
import ru.inbox.savinov_vu.common.util.DateTimeUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.Set;



@Entity
@Table(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SecurityUser implements UserDetails {

  @Id
  private Integer id;

  private String login;

  private String password;

  private Boolean enabled;

  @Transient
  private String fullName;

  @CreatedDate
  private Date lastPasswordResetDate;


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


  public static SecurityUser of(User user) {
    return new SecurityUser(user.getId(), user.getLogin(), user.getPassword(), user.getEnabled(), user.getFullName(), DateTimeUtils.convertLocalDateTimeToDate(user.getLastPasswordResetDate()));
  }

}
