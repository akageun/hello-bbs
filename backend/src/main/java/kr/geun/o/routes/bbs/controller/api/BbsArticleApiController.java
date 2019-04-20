package kr.geun.o.routes.bbs.controller.api;

import kr.geun.o.routes.bbs.dto.BbsArticleDTO;
import kr.geun.o.app.bbs.model.BbsArticleEntity;
import kr.geun.o.app.bbs.service.BbsArticleApiService;
import kr.geun.o.core.constants.CmnConst;
import kr.geun.o.core.controller.BaseController;
import kr.geun.o.core.response.ResData;
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
 * 글 관련 Api
 *
 * @author akageun
 */
@RestController
@RequestMapping("/api/bbs/v1")
public class BbsArticleApiController extends BaseController {

	@Autowired
	private BbsArticleApiService bbsArticleApiService;

	/**
	 * 리스트
	 *
	 * @param param
	 * @param result
	 * @return
	 */
	@GetMapping("/article")
	public ResponseEntity<ResData> getArticlePage(@Valid BbsArticleDTO.Page param, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(ResData.of(result));
		}

		Sort sort = new Sort(Sort.Direction.DESC, "articleId");
		Pageable pageable = PageRequest.of(param.getPageNumber(), CmnConst.RECORD_PER_COUNT, sort);

		Page<BbsArticleEntity> resultPage = bbsArticleApiService.page(pageable);

		Map<String, Object> rtnMap = new HashMap<>();

		rtnMap.put("resultList", resultPage.getContent());

		setPagination(rtnMap, resultPage, 5);

		return ResponseEntity.ok().body(ResData.of(rtnMap, "성공"));
	}

	/**
	 * 단건조회
	 *
	 * @param param
	 * @param result
	 * @return
	 */
	@GetMapping("/article/{articleId}")
	public ResponseEntity<ResData> getArticleOne(@Valid BbsArticleDTO.Get param, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(ResData.of(result));
		}

		BbsArticleEntity dbInfo = bbsArticleApiService.get(param.getArticleId());
		if (dbInfo == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResData.of("데이터를 찾을 수 없습니다."));
		}

		return ResponseEntity.ok().body(ResData.of(dbInfo, "성공"));
	}

	/**
	 * 글 추가
	 *
	 * @param param
	 * @param result
	 * @return
	 */
	@PostMapping("/article")
	public ResponseEntity<ResData> addArticle(@Valid BbsArticleDTO.Add param, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(ResData.of(result));
		}

		bbsArticleApiService.addArticle(param.getTitle(), param.getContent(), param.getStatusCd(), param.getCategoryId());

		return CmnConst.RES;
	}

	/**
	 * 글 수정
	 *
	 * @param param
	 * @param result
	 * @return
	 */
	@PutMapping("/article/{articleId}")
	public ResponseEntity<ResData> modifyArticle(@Valid BbsArticleDTO.Modify param, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(ResData.of(result));
		}

		BbsArticleEntity dbInfo = bbsArticleApiService.get(param.getArticleId());
		if (dbInfo == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResData.of("데이터를 찾을 수 없습니다."));
		}

		bbsArticleApiService.modifyArticle(param.getArticleId(), param.getTitle(), param.getContent(), param.getStatusCd(), param.getCategoryId());

		return CmnConst.RES;
	}
}