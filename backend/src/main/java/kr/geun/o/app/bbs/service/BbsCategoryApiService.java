package kr.geun.o.app.bbs.service;

import kr.geun.o.app.bbs.model.BbsCategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 카테고리 관련 API 서비스
 *
 * @author akageun
 */
public interface BbsCategoryApiService {

	/**
	 * 리스트
	 *
	 * @param pageable
	 * @return
	 */
	Page<BbsCategoryEntity> page(Pageable pageable);

	/**
	 * 단건조회
	 *
	 * @param categoryId
	 * @return
	 */
	BbsCategoryEntity get(Long categoryId);

	/**
	 * 추가
	 *
	 * @param type
	 * @param name
	 * @return
	 */
	void add(String type, String name);

	/**
	 * 수정
	 *
	 * @param categoryId
	 * @param type
	 * @param name
	 * @return
	 */
	void modify(Long categoryId, String type, String name);

	/**
	 * 검색
	 *
	 * @param keyword
	 * @return
	 */
	List<BbsCategoryEntity> search(String keyword);
}
