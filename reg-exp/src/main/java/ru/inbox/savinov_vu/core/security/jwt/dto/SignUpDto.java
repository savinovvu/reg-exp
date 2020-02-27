package ru.inbox.savinov_vu.core.security.jwt.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import ru.inbox.savinov_vu.app.users.model.Sex;
import ru.inbox.savinov_vu.core.jacksonConverter.sex.SexDeserializer;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;



@Data
public class SignUpDto {

  @NotNull
  @Size(min = 1, max = 255)
  private String firstName;

  @NotNull
  @Size(min = 1, max = 255)
  private String lastName;

  @NotNull
  @Size(min = 5, max = 255)
  private String login;

  @Email
  private String email;

  @JsonDeserialize(converter = SexDeserializer.class)
  private Sex sex;

  private LocalDate birthDate;

  @Size(min = 5, max = 255)
  private String password;

  @Size(min = 5, max = 255)
  private String repeatPassword;


}
