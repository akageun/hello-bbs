package kr.geun.o.core.exception;

import org.springframework.http.HttpStatus;

/**
 * @author akageun
 */
public class BaseException extends RuntimeException {

    private HttpStatus status;

    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
