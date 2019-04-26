package kr.geun.o.app.bbs.service;

import kr.geun.o.app.bbs.model.BbsArticleEntity;
import kr.geun.o.app.bbs.repository.BbsArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BbsArticleApiServiceTest {

    @MockBean
    private BbsArticleRepository bbsArticleRepository;

    @Autowired
    private BbsArticleApiService bbsArticleApiService;

    @Test
    public void getSuccessTest() {

        final Long articleId = 1L;
        final String title = "Test Title";

        GIVEN:
        {
            BbsArticleEntity param = BbsArticleEntity.builder()
                    .articleId(articleId)
                    .title(title)
                    .build();

            given(bbsArticleRepository.findByArticleId(articleId)).willReturn(param);
        }

        Optional<BbsArticleEntity> optResult;

        WHEN:
        {
            optResult = bbsArticleApiService.get(articleId);
        }

        THEN:
        {
            Assert.assertTrue(optResult.isPresent());

            BbsArticleEntity result = optResult.get();
            Assert.assertEquals(result.getTitle(), title);
        }

    }

    @Test
    public void emptyListSuccessTest() {
        final int recordCount = 10;
        Pageable pageable;

        GIVEN:
        {
            pageable = PageRequest.of(0, recordCount);
            given(bbsArticleRepository.findAll(pageable)).willReturn(new PageImpl<>(Collections.emptyList()));

        }


        Page<BbsArticleEntity> page;

        WHEN:
        {
            page = bbsArticleApiService.page(pageable);
        }

        THEN:
        {
            Assert.assertEquals(page.getTotalElements(), 0);
        }
    }
}