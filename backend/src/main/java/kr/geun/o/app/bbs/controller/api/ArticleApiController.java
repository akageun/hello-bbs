package kr.geun.o.app.bbs.controller.api;

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
public class ArticleApiController {

	@GetMapping("/article")
	public String getArticle() {
		return "TEST";
	}
}
