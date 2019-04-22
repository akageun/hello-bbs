package kr.geun.o.app.bbs.service;

import kr.geun.o.app.bbs.model.BbsArticleEntity;
import kr.geun.o.app.bbs.repository.BbsArticleRepository;
import kr.geun.o.core.exception.BaseException;
import kr.geun.o.core.utils.SecUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 게시글 관련 API 서비스
 *
 * @author akageun
 */
@Slf4j
@Service
public class BbsArticleApiService {

    @Autowired
    private BbsArticleRepository bbsArticleRepository;

    /**
     * 리스트 페이지 정보
     *
     * @param pageable
     * @return
     */
    public Page<BbsArticleEntity> page(Pageable pageable) {
        return bbsArticleRepository.findAll(pageable);
    }

    /**
     * 단건조회
     *
     * @param articleId
     * @return
     */
    public BbsArticleEntity get(Long articleId) {
        return bbsArticleRepository.findByArticleId(articleId);
    }

    public void preAddArticle(BbsArticleEntity dbParam) throws Exception {
        //blacklist에 등록된 유저 인지 확인

        //도배기능 확인

        //유사글 판단.
    }

    @Transactional
    public void addArticle(BbsArticleEntity dbParam) {
        bbsArticleRepository.add(dbParam);
    }

    /**
     * 글쓰기
     *
     * @param title
     * @param content
     * @param statusCd
     * @param categoryId
     */
    @Deprecated
    @Transactional
    public void addArticle(String title, String content, String statusCd, Long categoryId) {

        String userId = SecUtils.getUserName();

        //@formatter:off
		BbsArticleEntity dbParam = BbsArticleEntity
			.builder()
				.title(title)
				.content(content)
				.statusCd(statusCd)
				.categoryId(categoryId)
				.createdUserId(userId)
				.updatedUserId(userId)
			.build();
		//@formatter:on

        bbsArticleRepository.add(dbParam);
    }

    public void preModifyArticle(BbsArticleEntity dbParam) {
        BbsArticleEntity dbInfo = this.get(dbParam.getArticleId());
        if (dbInfo == null) {
            throw new BaseException("데이터를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);
        }


        if (StringUtils.equals(dbInfo.getCreatedUserId(), dbParam.getUpdatedUserId()) == false) {
            throw new BaseException("작성자만 글을 수정할 수 있습니다.", HttpStatus.BAD_REQUEST);
        }

    }

    @Transactional
    public void modifyArticle(BbsArticleEntity dbParam) {
        bbsArticleRepository.update(dbParam);
    }

    /**
     * 글 수정
     *
     * @param articleId
     * @param title
     * @param content
     * @param statusCd
     * @param categoryId
     */
    @Deprecated
    @Transactional
    public void modifyArticle(Long articleId, String title, String content, String statusCd, Long categoryId) {
        String userId = SecUtils.getUserName();

        //@formatter:off
		BbsArticleEntity dbParam = BbsArticleEntity
			.builder()
				.articleId(articleId)
				.title(title)
				.content(content)
				.statusCd(statusCd)
				.categoryId(categoryId)
				.createdUserId(userId)
				.updatedUserId(userId)
			.build();
		//@formatter:on

        bbsArticleRepository.update(dbParam);
    }

}
