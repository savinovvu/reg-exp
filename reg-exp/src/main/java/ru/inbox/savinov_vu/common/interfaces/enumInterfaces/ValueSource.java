package ru.inbox.savinov_vu.common.interfaces.enumInterfaces;

/**
 * Defines value accessor for enum
 */
public interface ValueSource<T> {

    /**
     * Get source value
     *
     * @return string representation of value
     */
    T getValue();
}
