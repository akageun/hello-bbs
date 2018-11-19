package kr.geun.o.app.user.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 이미 존재하는 아이디값에 대한 Exception
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
