package kr.geun.o.app.bbs.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 *
 *
 * @author akageun
 */
public class BbsArticleDTO {

	@Data
	@Builder
	public static class List {
		@Min(0)
		private int pageNumber;
	}

	@Data
	@Builder
	public static class Add {

		@NotBlank
		private String statusCd;
		@NotBlank
		private String title;
		@NotBlank
		private String content;
	}

	@Data
	@Builder
	public static class Get {

		@Min(0)
		public Long articleId;
	}
}
