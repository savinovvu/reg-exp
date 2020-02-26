package ru.inbox.savinov_vu.common.paged;


import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ru.inbox.savinov_vu.common.interfaces.DtoEntityMapper;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;



/**
 * Stores entities with supporting pagination
 */
@Value(staticConstructor = "of")
public class PagedResultList<T> implements PagedResult<List<T>> {

  /**
   * Result items
   */
  private List<T> items;

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


  /**
   * Constructs {@link PagedResultList} from {@link Page}
   *
   * @param page {@link Page}
   * @param <T>  type of data
   * @return {@link PagedResultList} constructed from {@link Page}
   */
  public static <T> PagedResultList<T> ofPage(Page<T> page) {
    checkArgument(page != null, "Page object cannot be null");
    return new PagedResultList<>(page.getContent(), page.getSize(), page.getNumber() + 1, page.getTotalElements(), page.getTotalPages());
  }


  public static <E, D> PagedResultList<D> ofPage(Page<E> page, DtoEntityMapper<D, E> dtoEntityMapper) {
    checkArgument(page != null, "Page object cannot be null");
    List<D> content = page.stream().map(v -> dtoEntityMapper.mapEntityToDto(v)).collect(Collectors.toList());
    return new PagedResultList<D>(content, page.getSize(), page.getNumber() + 1, page.getTotalElements(), page.getTotalPages());
  }


  /**
   * Constructs {@link PagedResultList} with specified max pages from {@link Page}
   *
   * @param page {@link Page}
   * @param <T>  type of data
   * @return {@link PagedResultList} constructed from {@link Page}
   */
  public static <T> PagedResultList<T> ofPage(Page<T> page, final Integer maxPageNumber) {
    checkArgument(page != null, "Page object cannot be null");
    return new PagedResultList<>(page.getContent(), page.getSize(), page.getNumber() + 1, page.getTotalElements(),
      page.getTotalPages() > maxPageNumber ? maxPageNumber : page.getTotalPages());
  }


  public static <T> PagedResultList<T> withResults(Page page, List<T> results) {
    checkArgument(page != null, "Page object cannot be null");
    checkArgument(results != null, "Results cannot be null");
    return new PagedResultList<>(results, page.getSize(), page.getNumber() + 1, page.getTotalElements(), page.getTotalPages());
  }


  /**
   * Method that add possibility to paginate data from your input list
   */
  public static <T> PagedResultList<T> fromList(@Nonnull List<T> list, @Nonnull PageRequest pageRequest) {
    Integer start = pageRequest.getPageNumber() * pageRequest.getPageSize();

    Integer end = (start + pageRequest.getPageSize()) > list.size()
      ? list.size()
      : (start + pageRequest.getPageSize());

    Page<T> pageData = new PageImpl<>(list.subList(start, end), pageRequest, list.size());

    return PagedResultList.of(
      pageData.getContent(),
      pageData.getSize(),
      pageData.getNumber() + 1,
      pageData.getTotalElements(),
      pageData.getTotalPages());
  }
}

