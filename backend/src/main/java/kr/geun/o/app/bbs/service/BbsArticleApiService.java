package kr.geun.o.app.bbs.service;

import kr.geun.o.app.bbs.model.BbsArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 *
 * @author akageun
 */
public interface BbsArticleApiService {

	Page<BbsArticleEntity> page(Pageable pageable);

	void addArticle(String title, String content, String status);

	BbsArticleEntity get(Long articleId);
}
