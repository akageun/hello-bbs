package kr.geun.o.config;

import kr.geun.o.app.bbs.model.BbsArticleEntity;
import kr.geun.o.app.bbs.model.BbsCategoryEntity;
import kr.geun.o.app.bbs.repository.BbsArticleRepository;
import kr.geun.o.app.bbs.repository.BbsCategoryRepository;
import kr.geun.o.app.user.model.UserAuthEntity;
import kr.geun.o.app.user.model.UserEntity;
import kr.geun.o.app.user.repository.UserAuthRepository;
import kr.geun.o.app.user.repository.UserRepository;
import kr.geun.o.common.constants.AuthorityCd;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author akageun
 */
@Slf4j
@Component
public class TempApplicationRunner implements CommandLineRunner {
	@Autowired
	private UserAuthRepository userAuthRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private BbsArticleRepository bbsArticleRepository;

	@Autowired
	private BbsCategoryRepository bbsCategoryRepository;

	@Override
	public void run(String... args) throws Exception {
		String userId = "test";

		UserEntity userEntityParam = UserEntity.builder().userId(userId).passWd(passwordEncoder.encode("test")).build();
		userRepository.save(userEntityParam);

		UserAuthEntity userAuthEntityParam = UserAuthEntity.builder().userId(userId).authorityCd(AuthorityCd.USER.roleCd()).build();
		userAuthRepository.save(userAuthEntityParam);

		List<BbsCategoryEntity> bbsCategoryEntities = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			bbsCategoryEntities.add(BbsCategoryEntity.builder().name("테스트 " + i).type("success").createdUserId(userId).updatedUserId(userId).build());
		}

		List<BbsCategoryEntity> bbsCategoryEntities1 = bbsCategoryRepository.saveAll(bbsCategoryEntities);

		List<BbsArticleEntity> article = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			article.add(BbsArticleEntity.builder().title("테스트 " + i).content("## 테스트 글 입니다.").statusCd("NORMAL").createdUserId(userId).categoryId(
				bbsCategoryEntities1.get(RandomUtils.nextInt(0, 4)).getCategoryId()).updatedUserId(userId).build());
		}

		bbsArticleRepository.saveAll(article);

	}
}
