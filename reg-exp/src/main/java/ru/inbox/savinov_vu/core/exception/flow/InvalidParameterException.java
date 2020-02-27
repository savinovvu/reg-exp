package ru.inbox.savinov_vu.core.exception.flow;


import ru.inbox.savinov_vu.core.exception.FlowException;



/**
 * Signals that incorrect parameter was passed to method/constructor
 * @author Morenets
 *
 */
public class InvalidParameterException extends FlowException {

	private static final long serialVersionUID = 4990959228756992926L;

	public InvalidParameterException(String message) {
		super(message);
	}	
}
