package kr.geun.o.app.bbs.repository;

import kr.geun.o.app.bbs.model.BbsArticleEntity;

/**
 * 게시글 관련 커스텀 Repo
 *
 * @author akageun
 */
public interface BbsArticleRepoDsl {

	/**
	 * 게시글 추가
	 *
	 * @param param
	 */
	void addArticle(BbsArticleEntity param);

	/**
	 * 게시글 수정
	 *
	 * @param param
	 */
	void updateArticle(BbsArticleEntity param);
}
