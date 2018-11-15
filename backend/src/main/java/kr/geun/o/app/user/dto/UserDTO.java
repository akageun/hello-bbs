package kr.geun.o.app.user.dto;

import lombok.Builder;
import lombok.Data;

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
		private String userId;
		private String passWd;
	}

	/**
	 * 회원가입
	 */
	@Data
	@Builder
	public static class SignUp {
		private String userId;
		private String passWd;
		private String confirmPassWd;
	}
}
