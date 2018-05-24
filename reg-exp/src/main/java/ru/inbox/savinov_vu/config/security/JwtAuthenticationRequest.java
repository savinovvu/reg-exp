package ru.inbox.savinov_vu.config.security;

import java.io.Serializable;



public class JwtAuthenticationRequest implements Serializable {

    private static final long serialVersionUID = -8445943548965154778L;

    public String login;

    public String password;


}
