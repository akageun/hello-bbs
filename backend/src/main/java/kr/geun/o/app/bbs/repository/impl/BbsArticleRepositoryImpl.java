package kr.geun.o.app.bbs.repository.impl;

import kr.geun.o.app.bbs.model.BbsArticleEntity;
import kr.geun.o.app.bbs.repository.BbsArticleRepoDsl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 *
 * @author akageun
 */
@Repository
public class BbsArticleRepositoryImpl implements BbsArticleRepoDsl {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void saveArticle(BbsArticleEntity param) {

        em.persist(param);
    }
}
