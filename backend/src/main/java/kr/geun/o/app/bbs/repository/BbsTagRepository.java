package kr.geun.o.app.bbs.repository;

import kr.geun.o.app.bbs.model.BbsTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 태그 Repo
 *
 * @author akageun
 */
public interface BbsTagRepository extends JpaRepository<BbsTagEntity, Long> {
}
