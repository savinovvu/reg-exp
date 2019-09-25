package ru.inbox.savinov_vu.common.interfaces.entityInterfaces;

import org.springframework.data.domain.Persistable;



public interface Identify extends Persistable {

    @Override
    Integer getId();

    default void setId(Integer newId) {
    }

    @Override
    default boolean isNew() {
        return (getId() == null);
    }

}
