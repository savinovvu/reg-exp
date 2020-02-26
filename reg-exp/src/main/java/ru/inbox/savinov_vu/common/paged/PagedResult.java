package ru.inbox.savinov_vu.common.paged;

/**
 * Interface for supporting pagination
 */
public interface PagedResult<T> {
    /**
     * Result items
     */
    T getItems();

    /**
     * Page size
     */
    int getSize();

    /**
     * page number
     */
    int getPage();

    /**
     * total rows count
     */
    long getTotal();

    /**
     * total pages count
     */
    long getTotalPages();
}
