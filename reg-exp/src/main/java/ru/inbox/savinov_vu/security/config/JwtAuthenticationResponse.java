package ru.inbox.savinov_vu.security.config;


import ru.inbox.savinov_vu.security.model.Authority;

import java.io.Serializable;
import java.util.Set;



public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    private final Integer id;

    private final String name;

    private final Set<Authority> roles;


    public JwtAuthenticationResponse(String token, Integer id, String name, Set<Authority> roles) {
        this.token = token;
        this.id = id;
        this.name = name;
        this.roles = roles;
    }


    public String getToken() {
        return this.token;
    }


    public Integer getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public Set<Authority> getRoles() {
        return roles;
    }
}
