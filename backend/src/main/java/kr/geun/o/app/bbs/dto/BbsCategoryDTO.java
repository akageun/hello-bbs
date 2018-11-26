package kr.geun.o.app.bbs.dto;

import lombok.Builder;
import lombok.Data;

/**
 *
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
