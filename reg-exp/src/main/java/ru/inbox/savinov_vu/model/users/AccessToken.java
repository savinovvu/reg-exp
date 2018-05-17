/*
package ru.inbox.savinov_vu.model.users;

import ru.inbox.savinov_vu.interfaces.Identify;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;



@Entity
public class AccessToken implements Identify {

    private Integer id;

    private UUID token;

    private User user;




    @Override
    public Integer getId() {
        return id;
    }


    public UUID getAuthority() {
        return token;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public void setAuthorities(UUID token) {
        this.token = token;
    }


    public void setUser(User user) {
        this.user = user;
    }
}
*/
