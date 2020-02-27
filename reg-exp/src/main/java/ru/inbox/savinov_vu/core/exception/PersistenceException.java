package ru.inbox.savinov_vu.core.exception;


import ru.inbox.savinov_vu.core.exception.base.AppException;


/**
 * Signals about incorrect configuration settings/parameters
 * @author Morenets
 *
 */
public class PersistenceException extends AppException {

	private static final long serialVersionUID = -7889716045779735512L;

	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersistenceException(String message) {
		super(message);
	}

}
