package kr.geun.o.app.bbs.repository;

import kr.geun.o.app.bbs.model.BbsArticleEntity;

/**
 *
 *
 * @author akageun
 */
public interface BbsArticleRepoDsl {

	void addArticle(BbsArticleEntity param);

	void updateArticle(BbsArticleEntity param);
}
