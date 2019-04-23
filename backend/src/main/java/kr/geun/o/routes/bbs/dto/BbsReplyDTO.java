package kr.geun.o.routes.bbs.dto;

import lombok.*;

import javax.validation.constraints.Min;

/**
 * 댓글 DTO
 *
 * @author akageun
 */
public class BbsReplyDTO {

	@Getter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.NONE)
	public static class Page {
		@Min(0)
		private int pageNumber;
	}

	@Getter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.NONE)
	public static class Get {

		@Min(0)
		@Setter
		private Long replyId;
	}
}
