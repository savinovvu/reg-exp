package ru.inbox.savinov_vu.core.security.jwt.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.inbox.savinov_vu.app.users.model.Sex;
import ru.inbox.savinov_vu.core.jacksonConverter.sex.SexDeserializer;
import ru.inbox.savinov_vu.core.jacksonConverter.sex.SexSerializer;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static java.util.Objects.isNull;



@Data
@Accessors(chain = true)
public class SignUpDto {

  @NotBlank
  @Size(min = 1, max = 255)
  private String firstName;

  @NotBlank
  @Size(min = 1, max = 255)
  private String lastName;

  @NotBlank
  @Size(min = 5, max = 255)
  private String login;

  @Email
  @NotBlank
  private String email;

  @NotNull
  @JsonDeserialize(converter = SexDeserializer.class)
  @JsonSerialize(converter = SexSerializer.class)
  private Sex sex;

  @NotNull
  private LocalDate birthDate;

  @NotNull
  @Size(min = 5, max = 255)
  private String password;

  @NotNull
  @Size(min = 5, max = 255)
  private String repeatPassword;


  public String getFirstName() {
    return isNull(firstName) ? null : firstName.strip();
  }


  public String getLastName() {
    return isNull(lastName) ? null : lastName.strip();
  }


  public String getLogin() {
    return isNull(login) ? null : login.strip();
  }


  public String getPassword() {
    return isNull(password) ? null : password.strip();
  }


  public String getRepeatPassword() {
    return isNull(repeatPassword) ? null : repeatPassword.strip();
  }

}
