package kr.geun.o.routes.bbs.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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

		@NotNull
		@Min(0)
		private int pageNumber;
	}

	@Getter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.NONE)
	public static class Get {

		@NotNull
		@Min(0)
		@Setter
		private Long replyId;
	}
}
