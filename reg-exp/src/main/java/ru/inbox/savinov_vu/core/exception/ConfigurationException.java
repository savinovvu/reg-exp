package ru.inbox.savinov_vu.core.exception;


import ru.inbox.savinov_vu.core.exception.base.AppException;


/**
 * Signals about incorrect configuration settings/parameters
 * @author Morenets
 *
 */
public class ConfigurationException extends AppException {

	private static final long serialVersionUID = -2177284893894040026L;

	public ConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConfigurationException(String message) {
		super(message);
	}

	public ConfigurationException(Throwable throwable) {
		super(throwable);
	}	
}
