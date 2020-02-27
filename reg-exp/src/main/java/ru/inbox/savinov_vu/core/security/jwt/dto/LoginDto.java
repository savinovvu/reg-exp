package ru.inbox.savinov_vu.core.security.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
  @NotNull
  @Size(min = 5, max = 255)
  private String login;

  @NotNull
  private String password;
}

