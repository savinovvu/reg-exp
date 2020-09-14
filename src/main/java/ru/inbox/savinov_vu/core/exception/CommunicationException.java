package ru.inbox.savinov_vu.core.exception;


import ru.inbox.savinov_vu.core.exception.base.AppException;



/**
 * Signals about exception cases in the work of external services and API
 */
public class CommunicationException extends AppException {

  private static final long serialVersionUID = -2850898723336164866L;


  public CommunicationException(String message, Throwable cause) {
    super(message, cause);
  }


  public CommunicationException(String message) {
    super(message);
  }

}
