package kr.geun.o.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * 공통 유틸 메소드
 *
 * @author akageun
 */
public class CmnUtils {

	/**
	 * @Valid 에서 출력된 메시지를 가져옴.
	 *
	 * @param result
	 * @param seperator
	 * @return
	 */
	public static String getErrMsg(BindingResult result, char seperator) {
		if (result.hasErrors() == false) { //오사용으로 인한 방어처리
			return "";
		}

		List<FieldError> errors = result.getFieldErrors();
		StringBuilder message = new StringBuilder();

		int errSize = errors.size();
		for (int i = 0; i < errSize; i++) {
			if (StringUtils.equals(errors.get(i).getCode(), "typeMismatch")) {
				message.append(errors.get(i).getField());
				message.append(" is type Mismatch");
			} else {
				message.append(errors.get(i).getDefaultMessage());
			}

			if (i != errSize - 1) {
				message.append(seperator);
			}
		}

		return message.toString();
	}
}
