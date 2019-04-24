package kr.geun.o.routes.user.dto;

import lombok.*;

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
	@Getter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.NONE)
	public static class Login {
		@NotBlank
		private String userId;
		@NotBlank
		private String passWd;
	}

	/**
	 * 회원가입
	 */
	@Getter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.NONE)
	public static class SignUp {
		@NotBlank
		private String userId;
		@NotBlank
		private String passWd;
		@NotBlank
		private String confirmPassWd;
	}
}
