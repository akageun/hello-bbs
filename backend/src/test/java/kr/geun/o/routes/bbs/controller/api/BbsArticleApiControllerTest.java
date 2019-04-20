package kr.geun.o.routes.bbs.controller.api;

import kr.geun.o.app.bbs.model.BbsArticleEntity;
import kr.geun.o.app.bbs.service.BbsArticleApiService;
import kr.geun.o.core.constants.CmnConst;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@WebMvcTest(value = { BbsArticleApiController.class })
public class BbsArticleApiControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private BbsArticleApiService bbsArticleApiService;

	@WithMockUser(value = "spring")
	@Test
	public void 권한있고_게시글목록_성공() throws Exception {
		MultiValueMap<String, String> mvcParam = new LinkedMultiValueMap<>();

		GIVEN:
		{
			int pageNumber = 1;

			mvcParam.add("pageNumber", String.valueOf(pageNumber));

			Sort sort = new Sort(Sort.Direction.DESC, "articleId");
			Pageable pageable = PageRequest.of(pageNumber, CmnConst.RECORD_PER_COUNT, sort);
			List<BbsArticleEntity> bbsArticleEntityList = new ArrayList<>();
			bbsArticleEntityList.add(BbsArticleEntity.builder().title("테스트").build());

			Page<BbsArticleEntity> impl = new PageImpl<>(bbsArticleEntityList);

			given(bbsArticleApiService.page(pageable)).willReturn(impl);
		}

		ResultActions result;
		WHEN:
		{
			//@formatter:off
			result = mvc.perform(
				get("/api/bbs/v1/article")
					.params(mvcParam)
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			);
			//@formatter:on
		}

		THEN:
		{
			//@formatter:off
			result
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
			//@formatter:on
		}
	}

}
