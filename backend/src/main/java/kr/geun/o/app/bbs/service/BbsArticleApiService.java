package kr.geun.o.app.bbs.service;

import kr.geun.o.app.bbs.model.BbsArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 게시글 관련 API 서비스
 *
 * @author akageun
 */
public interface BbsArticleApiService {

	/**
	 * 리스트 페이지 정보
	 *
	 * @param pageable
	 * @return
	 */
	Page<BbsArticleEntity> page(Pageable pageable);

	/**
	 * 단건조회
	 *
	 * @param articleId
	 * @return
	 */
	BbsArticleEntity get(Long articleId);

	/**
	 * 글쓰기
	 *
	 * @param title
	 * @param content
	 * @param statusCd
	 * @param categoryId
	 */
	void addArticle(String title, String content, String statusCd, Long categoryId);

	/**
	 * 글 수정
	 *
	 * @param articleId
	 * @param title
	 * @param content
	 * @param statusCd
	 * @param categoryId
	 */
	void modifyArticle(Long articleId, String title, String content, String statusCd, Long categoryId);
}
