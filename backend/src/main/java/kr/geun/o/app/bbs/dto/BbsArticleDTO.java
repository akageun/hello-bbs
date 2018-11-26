package kr.geun.o.app.bbs.dto;

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

		//TODO : status 체크
		@NotBlank
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

		//TODO : status 체크
		@NotBlank
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
