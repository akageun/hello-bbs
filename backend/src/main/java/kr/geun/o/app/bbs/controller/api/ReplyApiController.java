package kr.geun.o.app.bbs.controller.api;

import kr.geun.o.app.bbs.dto.BbsReplyDTO;
import kr.geun.o.common.response.ResData;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 댓글 API
 *
 * @author akageun
 */
@RestController
@RequestMapping("/api/bbs/v1")
public class ReplyApiController {

	@GetMapping("/reply")
	public ResponseEntity<ResData> getReply(@Valid BbsReplyDTO.Page param, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(ResData.of(result));
		}

		return ResponseEntity.ok(ResData.of("성공"));
	}

	@GetMapping("/reply/{replyId}")
	public ResponseEntity<ResData> getReply(@Valid BbsReplyDTO.Get param, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(ResData.of(result));
		}

		return ResponseEntity.ok(ResData.of("성공"));
	}
}
