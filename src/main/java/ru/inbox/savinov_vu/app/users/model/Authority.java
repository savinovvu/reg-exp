package ru.inbox.savinov_vu.app.users.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import ru.inbox.savinov_vu.common.interfaces.enumInterfaces.DataEnum;

import java.util.Arrays;

import static java.util.Objects.isNull;
import static ru.inbox.savinov_vu.app.users.model.Authority.AuthorityName.ROLE_ADMIN;
import static ru.inbox.savinov_vu.app.users.model.Authority.AuthorityName.ROLE_USER;



@Getter
@AllArgsConstructor
public enum Authority implements GrantedAuthority, DataEnum<String> {
  USER("user", "User", "пользователь", ROLE_USER),
  ADMIN("admin", "Admin", "Администратор", ROLE_ADMIN);

  private final String value;

  private final String enLabel;

  private final String ruLabel;

  private final String authorityName;


  @Override
  public String getAuthority() {
    return this.getAuthorityName();
  }


  public static Authority getByValue(String value) {
    if (isNull(value)) {
      return null;
    }
    return Arrays.stream(values()).filter(v -> v.getValue().equals(value))
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException(String.format("Wrong %s value: %s",
        Authority.class.getName(), value)));
  }


  public static class AuthorityName {

    public static final String USER = "USER";

    public static final String ROLE_USER = "ROLE_USER";

    public static final String ADMIN = "ADMIN";

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
  }
}
