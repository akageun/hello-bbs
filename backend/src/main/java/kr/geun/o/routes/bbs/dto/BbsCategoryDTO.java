package kr.geun.o.routes.bbs.dto;

import lombok.*;

/**
 * 카테고리 DTO
 *
 * @author akageun
 */
public class BbsCategoryDTO {

	@Getter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.NONE)
	public static class Search {
		private String keyword;
	}
}
