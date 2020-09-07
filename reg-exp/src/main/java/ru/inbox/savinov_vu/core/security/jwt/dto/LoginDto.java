package ru.inbox.savinov_vu.core.security.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static java.util.Objects.isNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

  @NotBlank
  @Size(min = 5, max = 255, message = "Login size must be between 5 and 255")
  private String login;

  @NotBlank(message = "Password must be not blank")
  private String password;

  public String getLogin() {
    return isNull(login) ? null : login.strip();
  }

  public String getPassword() {
    return isNull(password) ? null : password.strip();
  }
}

