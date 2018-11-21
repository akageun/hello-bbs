package kr.geun.o.common.constants;

import kr.geun.o.common.response.ResData;
import org.springframework.http.ResponseEntity;

/**
 * 공통 상수값
 *
 * @author akageun
 */
public class CmnConst {

	public static final int RECORD_PER_COUNT = 10;

	public static final ResponseEntity<ResData> RES = ResponseEntity.ok().body(ResData.of("성공"));
}
