package kr.geun.o.app.admin.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;

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

}
