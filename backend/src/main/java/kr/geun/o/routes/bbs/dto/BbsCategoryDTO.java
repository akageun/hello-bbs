package kr.geun.o.routes.bbs.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 카테고리 DTO
 *
 * @author akageun
 */
public class BbsCategoryDTO {

	@Data
	@Builder
	public static class Search {
		private String keyword;
	}
}
