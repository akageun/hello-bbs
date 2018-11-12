package kr.geun.o.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author 김형근
 */
@RestController
public class BackendTestApi {

	@GetMapping("/api/test")
	public String test() {
		return "테스트";
	}
}
