package kr.geun.o.app.bbs.repository.impl;

import kr.geun.o.app.bbs.model.BbsCategoryEntity;
import kr.geun.o.app.bbs.repository.BbsCategoryRepoDsl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 *
 * @author akageun
 */
@Repository
public class BbsCategoryRepositoryImpl implements BbsCategoryRepoDsl {

	@PersistenceContext
	private EntityManager em;

	/**
	 *  추가
	 *
	 * @param param
	 */
	@Override
	public void add(BbsCategoryEntity param) {
		em.persist(param);
	}

	/**
	 *  수정
	 *
	 * @param param
	 */
	@Override
	public void update(BbsCategoryEntity param) {
		em.merge(param);
	}
}
