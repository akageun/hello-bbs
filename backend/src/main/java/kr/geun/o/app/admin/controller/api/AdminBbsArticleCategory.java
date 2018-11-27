package kr.geun.o.app.admin.controller.api;

import kr.geun.o.app.admin.dto.AdminBbsArticleCategoryDTO;
import kr.geun.o.common.response.ResData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 게시글 관리 API
 *  - 카테고리 관리
 *
 * @author akageun
 */
@Slf4j
@RestController
@RequestMapping("/api/admin/bbs/article")
public class AdminBbsArticleCategory {

	/**
	 * 카테고리 목록
	 *
	 * @param param
	 * @param result
	 * @return
	 */
	@GetMapping("/category")
	public ResponseEntity<ResData> categoryPage(@Valid AdminBbsArticleCategoryDTO.Page param, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResData.of(result));
		}

		return ResponseEntity.ok(ResData.of("성공"));
	}
}
