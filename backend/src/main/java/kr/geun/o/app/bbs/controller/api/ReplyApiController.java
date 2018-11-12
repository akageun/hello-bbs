package kr.geun.o.app.bbs.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author akageun
 */
@RestController
@RequestMapping("/api/bbs/v1")
public class ReplyApiController {

	@GetMapping("/reply")
	public ResponseEntity<String> getReply() {
		return null;
	}
}
