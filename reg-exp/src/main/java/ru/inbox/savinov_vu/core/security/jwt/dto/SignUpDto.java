package ru.inbox.savinov_vu.core.security.jwt.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import ru.inbox.savinov_vu.app.users.model.Sex;
import ru.inbox.savinov_vu.core.jacksonConverter.sex.SexDeserializer;

import java.time.LocalDate;



@Data
public class SignUpDto {

  private String firstName;

  private String lastName;

  private String login;

  private String email;

  @JsonDeserialize(converter = SexDeserializer.class)
  private Sex sex;

  private LocalDate birthDate;

  private String password;

  private String repeatPassword;


}
