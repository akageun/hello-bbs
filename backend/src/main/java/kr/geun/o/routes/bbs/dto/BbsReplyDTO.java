package kr.geun.o.routes.bbs.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * 댓글 DTO
 *
 * @author akageun
 */
public class BbsReplyDTO {

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
		public Long replyId;
	}
}
