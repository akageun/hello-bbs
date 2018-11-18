package kr.geun.o.app.bbs.controller.api;

import kr.geun.o.app.bbs.dto.BbsArticleDTO;
import kr.geun.o.app.bbs.model.BbsArticleEntity;
import kr.geun.o.app.bbs.service.BbsArticleApiService;
import kr.geun.o.common.constants.CmnConst;
import kr.geun.o.common.pagination.PaginationInfo;
import kr.geun.o.common.response.ResData;
import kr.geun.o.common.utils.CmnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author akageun
 */
@RestController
@RequestMapping("/api/bbs/v1")
public class ArticleApiController {

	@Autowired
	private BbsArticleApiService bbsArticleApiService;

	@GetMapping("/article")
	public ResponseEntity<ResData> getArticle(@Valid BbsArticleDTO.List param, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(new ResData<>(CmnUtils.getErrMsg(result, '\n')), HttpStatus.BAD_REQUEST);
		}

		Sort sort = new Sort(Sort.Direction.DESC, "articleId");
		Pageable pageable = PageRequest.of(param.getPageNumber(), CmnConst.RECORD_PER_COUNT, sort);

		Page<BbsArticleEntity> resultList = bbsArticleApiService.page(pageable);

		Map<String, Object> rtnMap = new HashMap<>();

		//@formatter:off
        PaginationInfo paginationInfo = new PaginationInfo(
            resultList.getNumber(),
            resultList.getNumberOfElements(),
            resultList.getTotalElements(),
			resultList.getTotalPages(),
            pageable.getPageSize());
        //@formatter:on

		rtnMap.put("resultList", resultList.getContent());
		rtnMap.put("pagination", paginationInfo);

		return ResponseEntity.ok().body(new ResData<>(rtnMap, "성공"));
	}

	@GetMapping("/article/{articleId}")
	public ResponseEntity<ResData> getArticleOne(@Valid BbsArticleDTO.Get param, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(new ResData<>(CmnUtils.getErrMsg(result, '\n')), HttpStatus.BAD_REQUEST);
		}

		BbsArticleEntity dbInfo = bbsArticleApiService.get(param.getArticleId());
		if (dbInfo == null) {
			return new ResponseEntity<>(new ResData<>("데이터를 찾을 수 없습니다."), HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok().body(new ResData<>(dbInfo, "성공"));
	}

	@PostMapping("/article")
	public ResponseEntity<ResData> addArticle(@Valid BbsArticleDTO.Add param, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(new ResData<>(null, CmnUtils.getErrMsg(result, '\n')), HttpStatus.BAD_REQUEST);
		}

		bbsArticleApiService.addArticle(param.getTitle(), param.getContent(), param.getStatusCd());

		return ResponseEntity.ok().body(new ResData("TEST"));
	}
}
