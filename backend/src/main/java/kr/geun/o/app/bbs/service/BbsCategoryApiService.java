package kr.geun.o.app.bbs.service;

import kr.geun.o.app.bbs.model.BbsCategoryEntity;
import kr.geun.o.app.bbs.repository.BbsCategoryRepository;
import kr.geun.o.core.utils.SecUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 카테고리 관련 API 서비스
 *
 * @author akageun
 */
@Slf4j
@Service
public class BbsCategoryApiService {

    @Autowired
    private BbsCategoryRepository bbsCategoryRepository;

    /**
     * 리스트
     *
     * @param pageable
     * @return
     */
    public Page<BbsCategoryEntity> page(Pageable pageable) {
        return bbsCategoryRepository.findAll(pageable);
    }

    /**
     * 단건조회
     *
     * @param categoryId
     * @return
     */
    public BbsCategoryEntity get(Long categoryId) {
        return bbsCategoryRepository.getOne(categoryId);
    }

    /**
     * 추가
     *
     * @param type
     * @param name
     * @return
     */
    @Transactional
    public void add(String type, String name) {
        String userId = SecUtils.getUserName();

        //@formatter:off
		BbsCategoryEntity dbParam = BbsCategoryEntity
			.builder()
				.type(type)
				.name(name)
				.createdUserId(userId)
				.updatedUserId(userId)
			.build();
		//@formatter:on

        bbsCategoryRepository.add(dbParam);
    }

    /**
     * 수정
     *
     * @param categoryId
     * @param type
     * @param name
     * @return
     */
    @Transactional
    public void modify(Long categoryId, String type, String name) {
        String userId = SecUtils.getUserName();

        //@formatter:off
		BbsCategoryEntity dbParam = BbsCategoryEntity
			.builder()
				.categoryId(categoryId)
				.type(type)
				.name(name)
				.updatedUserId(userId)
			.build();
		//@formatter:on

        bbsCategoryRepository.update(dbParam);
    }

    /**
     * 검색
     *
     * @param keyword
     * @return
     */
    public List<BbsCategoryEntity> search(String keyword) {
        return bbsCategoryRepository.findByNameStartingWith(keyword);
    }
}
