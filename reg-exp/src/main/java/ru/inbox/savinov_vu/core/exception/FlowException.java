package ru.inbox.savinov_vu.core.exception;


import ru.inbox.savinov_vu.core.exception.base.AppException;


/**
 * Signals about incorrect configuration settings/parameters
 * @author Morenets
 *
 */
public class FlowException extends AppException {

	private static final long serialVersionUID = -2889607185988464072L;

	public FlowException(String message, Throwable cause) {
		super(message, cause);
	}

	public FlowException(String message) {
		super(message);
	}
}
