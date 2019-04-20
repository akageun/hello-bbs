package kr.geun.o.core.security.jwt;

import io.jsonwebtoken.ClaimJwtException;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 *
 *
 * @author akageun
 */
public interface JwtProvider {

	/**
	 * 토큰 생성
	 *
	 * @param authentication
	 * @return
	 */
	String generatorToken(Authentication authentication);

	/**
	 * 인증 정보 가져오기
	 *
	 * @param request
	 * @return
	 */
	Authentication getAuthentication(HttpServletRequest request) throws ClaimJwtException;

}
