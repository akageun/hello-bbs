package kr.geun.o.app.bbs.repository.impl;

import kr.geun.o.app.bbs.model.BbsArticleEntity;
import kr.geun.o.app.bbs.repository.BbsArticleRepoDsl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 게시글 관련 커스텀 Repo
 *
 * @author akageun
 */
@Repository
public class BbsArticleRepositoryImpl implements BbsArticleRepoDsl {

	@PersistenceContext
	private EntityManager em;

	/**
	 * 게시글 추가
	 *
	 * @param param
	 */
	@Override
	public void addArticle(BbsArticleEntity param) {
		em.persist(param);
	}

	/**
	 * 게시글 수정
	 *
	 * @param param
	 */
	@Override
	public void updateArticle(BbsArticleEntity param) {
		em.merge(param);
	}
}
