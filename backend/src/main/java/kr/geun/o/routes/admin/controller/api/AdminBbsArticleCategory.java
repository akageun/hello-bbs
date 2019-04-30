package kr.geun.o.routes.admin.controller.api;

import kr.geun.o.app.bbs.model.BbsCategoryEntity;
import kr.geun.o.app.bbs.service.BbsCategoryApiService;
import kr.geun.o.core.constants.CmnConst;
import kr.geun.o.core.controller.BaseController;
import kr.geun.o.core.exception.BaseException;
import kr.geun.o.core.response.ResData;
import kr.geun.o.core.utils.CmnUtils;
import kr.geun.o.core.utils.SecUtils;
import kr.geun.o.routes.admin.dto.AdminBbsArticleCategoryDTO;
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
import java.util.Optional;

/**
 * 게시글 관리 API
 * - 카테고리 관리
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
    public ResponseEntity<ResData> categoryPage(
            @Valid AdminBbsArticleCategoryDTO.Page param,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            throw new BaseException(CmnUtils.getErrMsg(result, '\n'), HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<ResData> getOne(
            @Valid AdminBbsArticleCategoryDTO.Get param,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            throw new BaseException(CmnUtils.getErrMsg(result, '\n'), HttpStatus.BAD_REQUEST);
        }

        Optional<BbsCategoryEntity> optDbInfo = bbsCategoryApiService.get(param.getCategoryId());
        if (optDbInfo.isPresent() == false) {
            throw new BaseException("데이터를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok().body(ResData.of(optDbInfo, "성공"));
    }

    /**
     * 카테고리 추가
     *
     * @param param
     * @param result
     * @return
     */
    @PostMapping("/category")
    public ResponseEntity<ResData> addCategory(
            @Valid AdminBbsArticleCategoryDTO.Add param,
            BindingResult result
    ) {
        
        if (result.hasErrors()) {
            throw new BaseException(CmnUtils.getErrMsg(result, '\n'), HttpStatus.BAD_REQUEST);
        }

        String userId = SecUtils.getUserName();

        //@formatter:off
		BbsCategoryEntity dbParam = BbsCategoryEntity
			.builder()
				.type(param.getType())
				.name(param.getName())
				.createdUserId(userId)
				.updatedUserId(userId)
			.build();
		//@formatter:on

        try {
            bbsCategoryApiService.add(dbParam);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

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
    public ResponseEntity<ResData> modifyArticle(
            @Valid AdminBbsArticleCategoryDTO.Modify param,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            throw new BaseException(CmnUtils.getErrMsg(result, '\n'), HttpStatus.BAD_REQUEST);
        }

        Optional<BbsCategoryEntity> optDbInfo = bbsCategoryApiService.get(param.getCategoryId());
        if (optDbInfo.isPresent() == false) {
            throw new BaseException("데이터를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }

        String userId = SecUtils.getUserName();

        BbsCategoryEntity dbParam = BbsCategoryEntity.builder()
                .categoryId(param.getCategoryId())
                .type(param.getType())
                .name(param.getName())
                .updatedUserId(userId)
                .build();

        try {
            bbsCategoryApiService.modify(dbParam);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return CmnConst.RES;
    }
}
