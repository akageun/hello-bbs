package kr.geun.o.app.bbs.repository;

import kr.geun.o.app.bbs.model.BbsCategoryEntity;

/**
 * 카테고리 Repo
 *
 * @author akageun
 */
public interface BbsCategoryRepoDsl {

	/**
	 *  추가
	 *
	 * @param param
	 */
	void add(BbsCategoryEntity param);

	/**
	 *  수정
	 *
	 * @param param
	 */
	void update(BbsCategoryEntity param);
}
