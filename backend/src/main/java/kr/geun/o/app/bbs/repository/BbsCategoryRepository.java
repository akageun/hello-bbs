package kr.geun.o.app.bbs.repository;

import kr.geun.o.app.bbs.model.BbsCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 카테고리 Repo
 *
 * @author akageun
 */
public interface BbsCategoryRepository extends JpaRepository<BbsCategoryEntity, Long> {
}
