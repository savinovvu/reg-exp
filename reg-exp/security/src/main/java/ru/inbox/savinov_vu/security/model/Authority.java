package ru.inbox.savinov_vu.security.model;

import org.springframework.security.core.GrantedAuthority;



public enum Authority implements GrantedAuthority {
    User, Admin;


    @Override
    public String getAuthority() {
        return this.toString();
    }
}
