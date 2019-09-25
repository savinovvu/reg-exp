package ru.inbox.savinov_vu.security.model;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.Date;
import java.util.Set;



// todo: split UserDetails and SecurityUser
@Entity
@Table(name = "\"user\"")
public class SecurityUser implements UserDetails {


    private Integer id;

    private String name;

    private String login;

    private String email;

    private String password;

    private Boolean enabled;

    private Set<Authority> authorities;



    private Date lastPasswordResetDate;


    @Id
    @SequenceGenerator(name = "GLOBAL_SEQ", sequenceName = "GLOBAL_SEQ", allocationSize = 1, initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GLOBAL_SEQ")
    public Integer getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getPassword() {
        return password;
    }

    @Column(unique = true)
    public String getLogin() {
        return login;
    }


    @Column(unique = true)
    public String getEmail() {
        return email;
    }


    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }


    //    UserDetails methods
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
