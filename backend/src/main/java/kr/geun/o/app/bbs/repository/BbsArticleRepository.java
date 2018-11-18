package kr.geun.o.app.bbs.repository;

import kr.geun.o.app.bbs.model.BbsArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 *
 * @author akageun
 */
public interface BbsArticleRepository extends JpaRepository<BbsArticleEntity, Long>, BbsArticleRepoDsl {

	BbsArticleEntity findByArticleId(Long articleId);
}
