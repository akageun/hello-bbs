package kr.geun.o.app.bbs.service.impl;

import kr.geun.o.app.bbs.model.BbsCategoryEntity;
import kr.geun.o.app.bbs.repository.BbsCategoryRepository;
import kr.geun.o.app.bbs.service.BbsCategoryApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 카테고리 관련 API 서비스
 *
 * @author akageun
 */
@Slf4j
@Service
public class BbsCategoryApiServiceImpl implements BbsCategoryApiService {

	@Autowired
	private BbsCategoryRepository bbsCategoryRepository;

	@Override
	public List<BbsCategoryEntity> search(String keyword) {
		return bbsCategoryRepository.findByNameStartingWith(keyword);
	}
}
