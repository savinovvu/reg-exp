package ru.inbox.savinov_vu.core.exception.base;

/**
 * Base class for all application-specific exceptions
 *
 */
public abstract class AppException extends RuntimeException {

  private static final long serialVersionUID = 7837501112802868980L;


  public AppException(String message, Throwable cause) {
    super(message, cause);
  }


  public AppException(String message) {
    super(message);
  }


  public AppException(Throwable throwable) {
    super(throwable);
  }
}
