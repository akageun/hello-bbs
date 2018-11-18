package kr.geun.o.app.bbs.service.impl;

import kr.geun.o.app.bbs.model.BbsArticleEntity;
import kr.geun.o.app.bbs.repository.BbsArticleRepository;
import kr.geun.o.app.bbs.service.BbsArticleApiService;
import kr.geun.o.common.utils.SecUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 *
 *
 * @author akageun
 */
@Slf4j
@Service
public class BbsArticleApiServiceImpl implements BbsArticleApiService {

    @Autowired
    private BbsArticleRepository bbsArticleRepository;

    @Override
    public Page<BbsArticleEntity> page(Pageable pageable) {
        return bbsArticleRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public void addArticle(String title, String content, String status) {

        String userId = SecUtils.getUserName();

        BbsArticleEntity dbParam = BbsArticleEntity.builder().title(title).content(content).statusCd(status).createdUserId(userId).updatedUserId(
            userId).build();

        bbsArticleRepository.saveArticle(dbParam);
    }

    @Override
    public BbsArticleEntity get(Long articleId) {
        return bbsArticleRepository.findByArticleId(articleId);
    }
}
