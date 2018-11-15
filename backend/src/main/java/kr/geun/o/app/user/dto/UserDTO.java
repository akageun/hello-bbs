package kr.geun.o.app.user.dto;

import lombok.Builder;
import lombok.Data;

/**
 *
 *
 * @author akageun
 */
public class UserDTO {

	@Data
	@Builder
	public static class Login {
		private String userId;
		private String passWd;
	}

	@Data
	@Builder
	public static class SignUp {
		private String userId;
		private String passWd;
	}
}
