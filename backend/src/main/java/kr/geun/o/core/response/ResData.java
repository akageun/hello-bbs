package kr.geun.o.core.response;

import kr.geun.o.core.utils.CmnUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

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

	public static <T> ResData of(final T data, final String msg) {
		return new ResData<>(data, msg);
	}

	public static ResData of(final String msg) {
		return new ResData<>(msg);
	}

	public static ResData of(final BindingResult result) {
		return new ResData<>(CmnUtils.getErrMsg(result, '\n'));
	}
}
