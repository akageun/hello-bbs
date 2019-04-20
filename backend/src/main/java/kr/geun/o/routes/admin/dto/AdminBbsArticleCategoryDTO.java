package kr.geun.o.routes.admin.dto;

import kr.geun.o.app.bbs.code.CategoryLabelTypeCd;
import kr.geun.o.core.valid.EnumValid;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * 게시글 관리 DTO
 *  - 카테고리
 *
 * @author akageun
 */
public class AdminBbsArticleCategoryDTO {

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
		private Long categoryId;
	}

	@Data
	@Builder
	public static class Add {
		@Size(max = 512, min = 1)
		private String name;

		@EnumValid(targetEnum = CategoryLabelTypeCd.class)
		private String type; //bootstrap label color 넣은 곳
	}

	@Data
	@Builder
	public static class Modify {
		@Min(0)
		private Long categoryId;

		@Size(max = 512, min = 1)
		private String name;

		@EnumValid(targetEnum = CategoryLabelTypeCd.class)
		private String type; //bootstrap label color 넣은 곳
	}
}
