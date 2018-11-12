package kr.geun.o.app.bbs.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author 김형근
 */
@RestController
@RequestMapping("/api")
public class BbsApiController {

	@GetMapping("/v1/bbs")
	public String getBbsList() {
		return "테스트";
	}
}
