package ru.inbox.savinov_vu.interfaces;

import org.springframework.data.domain.Persistable;



public interface Identify extends Persistable {

    @Override
    Integer getId();


    @Override
    default boolean isNew() {
        return (getId() == null);
    }

}
