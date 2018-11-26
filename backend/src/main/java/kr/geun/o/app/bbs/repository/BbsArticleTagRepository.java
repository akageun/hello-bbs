package kr.geun.o.app.bbs.repository;

import kr.geun.o.app.bbs.model.BbsArticleTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 게시글 Tag Mapping Repo
 *
 * @author akageun
 */
public interface BbsArticleTagRepository extends JpaRepository<BbsArticleTagEntity, Long> {
}
