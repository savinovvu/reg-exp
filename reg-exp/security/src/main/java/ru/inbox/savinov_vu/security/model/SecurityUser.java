package ru.inbox.savinov_vu.security.model;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Set;



// todo: split UserDetails and SecurityUser
@Entity
@Table(name = "\"user\"")
public class SecurityUser implements  UserDetails {


    private Integer id;

    private String login;

    private String password;

    private Boolean enabled;

    private Set<Authority> authorities;

    public SecurityUser() {
    }


    public SecurityUser(Integer id) {
        this.id = id;
    }


    @Override
    @Transient
    public String getUsername() {
        return login;
    }


    @Override
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(joinColumns = @JoinColumn(name = "user_id"))
    public Set<Authority> getAuthorities() {
        return authorities;
    }


    @Override
    public String getPassword() {
        return password;
    }


    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return enabled;
    }


}
