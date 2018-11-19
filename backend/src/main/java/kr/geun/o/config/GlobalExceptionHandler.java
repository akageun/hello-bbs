package kr.geun.o.config;

import kr.geun.o.common.response.ResData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception 핸들링
 *
 * @author akageun
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Exception 전체에 대한 핸들링
	 *
	 * @param e
	 * @return
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ResData handleException(Exception e) {
		return ResData.of(String.format("시스템에러가 발생했습니다. : {}", e.getMessage()));
	}

	/**
	 * 허용되지 않는 HTTP 메소드 요청시 에러 핸들러
	 *
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResData httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
		return ResData.of(String.format("Not allow your request : %s", ex.getMethod()));
	}

	/**
	 * 유효하지 않은 메시지 포맷 예외처리 핸들러(ex. 유효하지 않은 Json포맷)
	 *
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResData httpMessageNotReadableException(HttpMessageNotReadableException ex) {
		return ResData.of(String.format("HttpMessageNotReadable occurred. : %s", ex.getMessage()));
	}
}
