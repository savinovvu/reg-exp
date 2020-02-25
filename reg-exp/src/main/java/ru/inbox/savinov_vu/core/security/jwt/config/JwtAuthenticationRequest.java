package ru.inbox.savinov_vu.core.security.jwt.config;
import java.io.Serializable;



public class JwtAuthenticationRequest implements Serializable {

  private static final long serialVersionUID = -8445943548965154778L;

  public String login;

  public String password;


}
