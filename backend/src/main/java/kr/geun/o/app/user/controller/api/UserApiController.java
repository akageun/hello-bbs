package kr.geun.o.app.user.controller.api;

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
public class UserApiController {

	@GetMapping("/v1/user/signin")
	public String getUserInfo() {
		return "";
	}
}
