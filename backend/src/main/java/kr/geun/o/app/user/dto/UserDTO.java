package kr.geun.o.app.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 유저관련 DTO
 *
 * @author akageun
 */
public class UserDTO {

	/**
	 * 로그인
	 */
	@Data
	@Builder
	public static class Login {
		@NotBlank
		private String userId;
		@NotBlank
		private String passWd;
	}

	/**
	 * 회원가입
	 */
	@Data
	@Builder
	public static class SignUp {
		@NotBlank
		private String userId;
		@NotBlank
		private String passWd;
		@NotBlank
		private String confirmPassWd;
	}
}
