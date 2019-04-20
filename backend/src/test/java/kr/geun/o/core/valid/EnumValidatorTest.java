package kr.geun.o.core.valid;

import kr.geun.o.app.bbs.code.ArticleStatusCd;
import kr.geun.o.routes.bbs.dto.BbsArticleDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Enum Validator Test case
 *
 * @author akageun
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class EnumValidatorTest {

	private Validator validator;

	/**
	 * Test 실행 전 동작
	 */
	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void 글상태값_ENUM_테스트_성공() {
		//@formatter:off
		BbsArticleDTO.Add mockParam = BbsArticleDTO.Add.builder()
				.title("TITLE")
				.content("#TEST")
				.statusCd(ArticleStatusCd.NORMAL.name())
			.build();
		//@formatter:on

		Set<ConstraintViolation<BbsArticleDTO.Add>> violations = validator.validate(mockParam);

		//THEN(Verification)
		assertTrue(violations.isEmpty());
		assertEquals(0, violations.size());
	}

	@Test
	public void 글상태값_ENUM_테스트_실패() {
		//@formatter:off
		BbsArticleDTO.Add mockParam = BbsArticleDTO.Add.builder()
				.title("TITLE")
				.content("#TEST")
				.statusCd("ERROR_STATUS")
			.build();
		//@formatter:on

		Set<ConstraintViolation<BbsArticleDTO.Add>> violations = validator.validate(mockParam);

		//THEN(Verification)
		assertFalse(violations.isEmpty());
		assertEquals(1, violations.size());
	}
}
