package ru.inbox.savinov_vu.common.paged;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;



/**
 * Stores entities with supporting pagination
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PagedResultMap<K, V> implements PagedResult<Map<K, V>> {
    /**
     * Result items
     */
    private Map<K, V> items;

    /**
     * Page size
     */
    private int size;

    /**
     * page number
     */
    private int page;

    /**
     * total rows count
     */
    private long total;

    /**
     * total pages count
     */
    public long totalPages;

    public static <K, V> PagedResultMap<K, V> withResults(Page page, Map<K, V> results) {
        checkArgument(page != null, "Page object cannot be null");
        checkArgument(results != null, "Results cannot be null");
        return new PagedResultMap<>(results, page.getSize(), page.getNumber() + 1, page.getTotalElements(),
            page.getTotalPages());
    }
}
