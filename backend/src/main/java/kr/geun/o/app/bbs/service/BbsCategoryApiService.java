package kr.geun.o.app.bbs.service;

import kr.geun.o.app.bbs.model.BbsCategoryEntity;

import java.util.List;

/**
 * 카테고리 관련 API 서비스
 *
 * @author akageun
 */
public interface BbsCategoryApiService {

	List<BbsCategoryEntity> search(String keyword);
}
