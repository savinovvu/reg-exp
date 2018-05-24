package ru.inbox.savinov_vu.model.users;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import ru.inbox.savinov_vu.interfaces.Identify;

import javax.persistence.*;
import java.util.List;



@Entity
public class Authority implements Identify, GrantedAuthority {


    private Integer id;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private AuthorityName name;

    private List<User> users;


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


    @Transient
    public String getAuthority() {
        return name.name();
    }


    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    public AuthorityName getName() {
        return name;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public void setName(AuthorityName name) {
        this.name = name;
    }


    public void setUsers(List<User> users) {
        this.users = users;
    }
}
