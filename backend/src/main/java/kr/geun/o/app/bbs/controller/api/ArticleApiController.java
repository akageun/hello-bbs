package kr.geun.o.app.bbs.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author 김형근
 */
@RestController
@RequestMapping("/api/bbs/v1")
public class ArticleApiController {

	@GetMapping("/article")
	public ResponseEntity<String> getArticle() {
		return null;
	}
}
