package kr.geun.o.routes.admin.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 게시글 관리 DTO
 *
 * @author akageun
 */
public class AdminBbsArticleTagDTO {

	@Getter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.NONE)
	public static class Page {
		@NotNull
		@Min(0)
		private int pageNumber;
	}

}
