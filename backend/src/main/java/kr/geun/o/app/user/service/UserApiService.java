package kr.geun.o.app.user.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 *
 * @author akageun
 */
public interface UserApiService {

	/**
	 * 유저 정보 가져오기
	 *
	 * @param userId
	 * @param passWd
	 * @return
	 */
	UserDetails getUserDetails(String userId, String passWd);

	/**
	 * 토큰 생성
	 *
	 * @param details
	 * @return
	 */
	String generatorToken(UserDetails details);
}
