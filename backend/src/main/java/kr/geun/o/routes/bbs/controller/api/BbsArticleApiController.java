package kr.geun.o.routes.bbs.controller.api;

import kr.geun.o.app.bbs.model.BbsArticleEntity;
import kr.geun.o.app.bbs.service.BbsArticleApiService;
import kr.geun.o.core.constants.CmnConst;
import kr.geun.o.core.controller.BaseController;
import kr.geun.o.core.exception.BaseException;
import kr.geun.o.core.response.ResData;
import kr.geun.o.core.utils.CmnUtils;
import kr.geun.o.core.utils.SecUtils;
import kr.geun.o.routes.bbs.dto.BbsArticleDTO;
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
 * 글 관련 Api
 *
 * @author akageun
 */
@Slf4j
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
    public ResponseEntity<ResData> getArticlePage(
            @Valid BbsArticleDTO.Page param,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            throw new BaseException(CmnUtils.getErrMsg(result, '\n'), HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<ResData> getArticleOne(
            @Valid BbsArticleDTO.Get param,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            throw new BaseException(CmnUtils.getErrMsg(result, '\n'), HttpStatus.BAD_REQUEST);
        }

        Optional<BbsArticleEntity> optDbInfo = bbsArticleApiService.get(param.getArticleId());
        if (optDbInfo.isPresent() == false) {
            throw new BaseException("데이터를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().body(ResData.of(optDbInfo.get(), "성공"));
    }

    /**
     * 글 추가
     *
     * @param param
     * @param result
     * @return
     */
    @PostMapping("/article")
    public ResponseEntity<ResData> addArticle(
            @Valid BbsArticleDTO.Add param,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            throw new BaseException(CmnUtils.getErrMsg(result, '\n'), HttpStatus.BAD_REQUEST);
        }

        String userId = SecUtils.getUserName();

        BbsArticleEntity dbParam = BbsArticleEntity.builder()
                .title(param.getTitle())
                .content(param.getContent())
                .statusCd(param.getStatusCd())
                .categoryId(param.getCategoryId())
                .createdUserId(userId)
                .updatedUserId(userId)
                .build();

        try {
            bbsArticleApiService.preAddArticle(dbParam); //전처리

            bbsArticleApiService.addArticle(dbParam);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }


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
    public ResponseEntity<ResData> modifyArticle(
            @Valid BbsArticleDTO.Modify param,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            throw new BaseException(CmnUtils.getErrMsg(result, '\n'), HttpStatus.BAD_REQUEST);
        }

        String userId = SecUtils.getUserName();

        BbsArticleEntity dbParam = BbsArticleEntity.builder()
                .articleId(param.getArticleId())
                .title(param.getTitle())
                .content(param.getContent())
                .statusCd(param.getStatusCd())
                .categoryId(param.getCategoryId())
                .createdUserId(userId)
                .updatedUserId(userId)
                .build();

        try {
            bbsArticleApiService.preModifyArticle(dbParam);
            bbsArticleApiService.modifyArticle(dbParam);


        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return CmnConst.RES;
    }
}
