package kr.geun.o.app.user.exception;

import org.springframework.security.core.AuthenticationException;

/**
 *
 *
 * @author akageun
 */
public class AlreadyUsernameException extends AuthenticationException {

	public AlreadyUsernameException(String msg) {
		super(msg);
	}

	public AlreadyUsernameException(String msg, Throwable t) {
		super(msg, t);
	}
}
