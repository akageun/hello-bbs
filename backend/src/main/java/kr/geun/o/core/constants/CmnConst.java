package kr.geun.o.core.constants;

import kr.geun.o.core.response.ResData;
import org.springframework.http.ResponseEntity;

/**
 * 공통 상수값
 *
 * @author akageun
 */
public class CmnConst {

	public static final int RECORD_PER_COUNT = 10;

	public static final ResponseEntity<ResData> RES = ResponseEntity.ok().body(ResData.of("성공"));

	public static final String YMDHMS_READONLY = "yyyy-MM-dd HH:mm:ss";
}
