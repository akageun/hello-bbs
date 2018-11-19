package kr.geun.o.app.bbs.service.impl;

import kr.geun.o.app.bbs.model.BbsArticleEntity;
import kr.geun.o.app.bbs.repository.BbsArticleRepository;
import kr.geun.o.app.bbs.service.BbsArticleApiService;
import kr.geun.o.common.utils.SecUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 게시글 관련 API 서비스
 *
 * @author akageun
 */
@Slf4j
@Service
public class BbsArticleApiServiceImpl implements BbsArticleApiService {

	@Autowired
	private BbsArticleRepository bbsArticleRepository;

	/**
	 * 리스트 페이지 정보
	 *
	 * @param pageable
	 * @return
	 */
	@Override
	public Page<BbsArticleEntity> page(Pageable pageable) {
		return bbsArticleRepository.findAll(pageable);
	}

	/**
	 * 단건조회
	 *
	 * @param articleId
	 * @return
	 */
	@Override
	public BbsArticleEntity get(Long articleId) {
		return bbsArticleRepository.findByArticleId(articleId);
	}

	/**
	 * 글쓰기
	 *
	 * @param title
	 * @param content
	 * @param statusCd
	 */
	@Transactional
	@Override
	public void addArticle(String title, String content, String statusCd) {

		String userId = SecUtils.getUserName();

		//@formatter:off
		BbsArticleEntity dbParam = BbsArticleEntity
			.builder()
				.title(title)
				.content(content)
				.statusCd(statusCd)
				.createdUserId(userId)
				.updatedUserId(userId)
			.build();
		//@formatter:on

		bbsArticleRepository.saveArticle(dbParam);
	}

	/**
	 * 글 수정
	 *  @param articleId
	 * @param title
	 * @param content
	 * @param statusCd
	 */
	@Override
	public void modifyArticle(Long articleId, String title, String content, String statusCd) {
		String userId = SecUtils.getUserName();

		//@formatter:off
		BbsArticleEntity dbParam = BbsArticleEntity
			.builder()
				.articleId(articleId)
				.title(title)
				.content(content)
				.statusCd(statusCd)
				.createdUserId(userId)
				.updatedUserId(userId)
			.build();
		//@formatter:on

		bbsArticleRepository.saveArticle(dbParam);
	}

}