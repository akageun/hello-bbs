package kr.geun.o.app.admin.controller.api;

import kr.geun.o.app.admin.dto.AdminBbsArticleCategoryDTO;
import kr.geun.o.app.bbs.model.BbsCategoryEntity;
import kr.geun.o.app.bbs.service.BbsCategoryApiService;
import kr.geun.o.common.constants.CmnConst;
import kr.geun.o.common.controller.BaseController;
import kr.geun.o.common.response.ResData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 게시글 관리 API
 *  - 카테고리 관리
 *
 * @author akageun
 */
@Slf4j
@RestController
@RequestMapping("/api/admin/bbs/article")
public class AdminBbsArticleCategory extends BaseController {

	@Autowired
	private BbsCategoryApiService bbsCategoryApiService;

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

		Sort sort = new Sort(Sort.Direction.DESC, "categoryId");
		Pageable pageable = PageRequest.of(param.getPageNumber(), CmnConst.RECORD_PER_COUNT, sort);

		Page<BbsCategoryEntity> resultPage = bbsCategoryApiService.page(pageable);

		Map<String, Object> rtnMap = new HashMap<>();

		rtnMap.put("resultList", resultPage.getContent());

		setPagination(rtnMap, resultPage, 5);

		return ResponseEntity.ok(ResData.of(rtnMap, "성공"));
	}

	/**
	 * 단건조회
	 *
	 * @param param
	 * @param result
	 * @return
	 */
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<ResData> getOne(@Valid AdminBbsArticleCategoryDTO.Get param, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(ResData.of(result));
		}

		BbsCategoryEntity dbInfo = bbsCategoryApiService.get(param.getCategoryId());
		if (dbInfo == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResData.of("데이터를 찾을 수 없습니다."));
		}

		return ResponseEntity.ok().body(ResData.of(dbInfo, "성공"));
	}

	/**
	 * 카테고리 추가
	 *
	 * @param param
	 * @param result
	 * @return
	 */
	@PostMapping("/category")
	public ResponseEntity<ResData> addCategory(@Valid AdminBbsArticleCategoryDTO.Add param, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(ResData.of(result));
		}

		bbsCategoryApiService.add(param.getType(), param.getName());

		return CmnConst.RES;
	}

	/**
	 * 카테고리 수정
	 *
	 * @param param
	 * @param result
	 * @return
	 */
	@PutMapping("/category/{categoryId}")
	public ResponseEntity<ResData> modifyArticle(@Valid AdminBbsArticleCategoryDTO.Modify param, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(ResData.of(result));
		}

		BbsCategoryEntity dbInfo = bbsCategoryApiService.get(param.getCategoryId());
		if (dbInfo == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResData.of("데이터를 찾을 수 없습니다."));
		}

		bbsCategoryApiService.modify(param.getCategoryId(), param.getType(), param.getName());

		return CmnConst.RES;
	}
}
