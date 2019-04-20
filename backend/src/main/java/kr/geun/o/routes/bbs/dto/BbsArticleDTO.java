package kr.geun.o.routes.bbs.dto;

import kr.geun.o.app.bbs.code.ArticleStatusCd;
import kr.geun.o.core.valid.EnumValid;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 게시글 관련 DTO
 *
 * @author akageun
 */
public class BbsArticleDTO {

	@Data
	@Builder
	public static class Page {
		@Min(0)
		private int pageNumber;
	}

	@Data
	@Builder
	public static class Get {

		@Min(0)
		public Long articleId;
	}

	@Data
	@Builder
	public static class Add {

		@EnumValid(targetEnum = ArticleStatusCd.class)
		private String statusCd;

		//TODO : 길이체크
		@NotBlank
		private String title;

		//TODO : 길이체크
		@NotBlank
		private String content;

		@Min(0)
		private Long categoryId;
	}

	@Data
	@Builder
	public static class Modify {

		@Min(0)
		public Long articleId;

		@EnumValid(targetEnum = ArticleStatusCd.class)
		private String statusCd;

		//TODO : 길이체크
		@NotBlank
		private String title;

		//TODO : 길이체크
		@NotBlank
		private String content;

		@Min(0)
		private Long categoryId;
	}
}
