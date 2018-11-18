package kr.geun.o.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 응답 데이터
 *
 * @author akageun
 */
@Getter
@AllArgsConstructor
public class ResData<T> {

	private T data;
	private String msg;

	public ResData(String msg) {
		this.msg = msg;
	}
}
