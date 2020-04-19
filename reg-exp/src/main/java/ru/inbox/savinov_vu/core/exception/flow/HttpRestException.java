package ru.inbox.savinov_vu.core.exception.flow;

import lombok.Getter;
import ru.inbox.savinov_vu.core.exception.FlowException;



/**
 * Invoked when web server returns error status code (4xx-5xx)
 */
@Getter
public class HttpRestException extends FlowException {

  private static final long serialVersionUID = -6408338683345172869L;

  /**
   * True if it's client error(4xx) or server error (5xx)
   */
  private final boolean client;


  public HttpRestException(String message, boolean client) {
    super(message);
    this.client = client;
  }
}
