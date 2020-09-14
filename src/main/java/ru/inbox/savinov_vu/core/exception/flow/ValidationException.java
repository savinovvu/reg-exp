package ru.inbox.savinov_vu.core.exception.flow;


import ru.inbox.savinov_vu.core.exception.FlowException;



public class ValidationException extends FlowException {

  private static final long serialVersionUID = 6858621613562789296L;


  public ValidationException(String message, Throwable cause) {
    super(message, cause);
  }

}
