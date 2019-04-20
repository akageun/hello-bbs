package kr.geun.o.routes.admin.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * 게시글 관리 DTO
 *
 * @author akageun
 */
public class AdminBbsArticleTagDTO {

	@Data
	@Builder
	public static class Page {
		@Min(0)
		private int pageNumber;
	}

}
