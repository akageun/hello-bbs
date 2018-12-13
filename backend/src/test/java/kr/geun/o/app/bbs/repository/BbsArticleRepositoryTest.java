package kr.geun.o.app.bbs.repository;

import kr.geun.o.app.bbs.model.BbsArticleEntity;
import kr.geun.o.app.bbs.model.BbsCategoryEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
//@Transactional(propagation = Propagation.NOT_SUPPORTED) //트랜젝션을 사용하지 않을 때 붙임.
public class BbsArticleRepositoryTest {

	@Autowired
	private BbsArticleRepository bbsArticleRepository;

	@Autowired
	private BbsCategoryRepository bbsCategoryRepository;

	@Test
	public void 글저장_테스트_성공() {
		BbsCategoryEntity bbsCategoryEntity = bbsCategoryRepository.save(BbsCategoryEntity.builder().name("테스트").type("label").build());

		final String title = "Sample Title.";
		final String content = "# Test Content ";
		final String statusCd = "NORMARL";
		final String userId = "akageun";

		//@formatter:off
		BbsArticleEntity mockArticleEntity = BbsArticleEntity
			.builder()
				//.articleId(articleId)
				.content(content)
				.title(title)
				.categoryId(bbsCategoryEntity.getCategoryId())
				.statusCd(statusCd)
				.createdUserId(userId)
				.updatedUserId(userId)
			.build();
		//@formatter:on

		bbsArticleRepository.add(mockArticleEntity);

		List<BbsArticleEntity> dbInfoEntity = bbsArticleRepository.findAll();
		//@formatter:off
		assertThat(dbInfoEntity)
			.isNotEmpty()
			.hasSize(1)
			.contains(mockArticleEntity);
		//@formatter:on
	}
}
