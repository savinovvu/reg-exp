package ru.inbox.savinov_vu.common.paged;

public interface PagedResult<T> {

  T getItems();

  int getSize();

  int getPage();

  long getTotal();

  long getTotalPages();
}
