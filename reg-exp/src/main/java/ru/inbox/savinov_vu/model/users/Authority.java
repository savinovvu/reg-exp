package ru.inbox.savinov_vu.model.users;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import ru.inbox.savinov_vu.interfaces.Identify;

import javax.persistence.*;
import java.util.List;



@Entity
public class Authority implements Identify, GrantedAuthority {

//    Admin, User, Guest;

    private Integer id;

    private String authority;

    List<User> users;


    @Id
    @SequenceGenerator(name = "GLOBAL_SEQ", sequenceName = "GLOBAL_SEQ", allocationSize = 1, initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GLOBAL_SEQ")
    public Integer getId() {
        return id;
    }


    @LazyCollection(value = LazyCollectionOption.TRUE)
    @ManyToMany(mappedBy = "authorities")
    public List<User> getUsers() {
        return users;
    }


    @Override
    @Column(unique = true)
    public String getAuthority() {
        return this.authority;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public void setAuthority(String authority) {
        this.authority = authority;
    }


    public void setUsers(List<User> users) {
        this.users = users;
    }
}
