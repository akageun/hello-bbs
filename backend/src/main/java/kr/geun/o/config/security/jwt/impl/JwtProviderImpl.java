package kr.geun.o.config.security.jwt.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kr.geun.o.common.utils.SecUtils;
import kr.geun.o.config.security.jwt.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 *
 *
 * @author akageun
 */
@Slf4j
public class JwtProviderImpl implements JwtProvider, InitializingBean {

	private static final String SECRET_KEY = "secretKey";

	private static final String JWT_PREFIX = "Bearer ";
	private static final String JWT_HEADER_STRING = "Authorization";

	private static final String AUTHORITIES_KEY_NM = "auth";
	private static final long TOKEN_EXPIRE_MS = 3600 * 1000; //1Hours
	private static final String TOKEN_CREATED_AT = "tca";

	/**
	 * 토큰 생성
	 *
	 * @param authentication
	 * @return
	 */
	@Override
	public String generatorToken(Authentication authentication) {
		String authorities = SecUtils.getAuthorities(authentication.getAuthorities());

		Date expireDate = new Date(System.currentTimeMillis() + TOKEN_EXPIRE_MS);
		//@formatter:off
		return Jwts.builder()
			.setSubject(authentication.getName())
			.claim(AUTHORITIES_KEY_NM, authorities)
			.claim(TOKEN_CREATED_AT, new Date())
			.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
			.setExpiration(expireDate)
			.compact();
		//@formatter:on
	}

	/**
	 * 인증 정보 가져오기
	 *
	 * @param request
	 * @return
	 */
	@Override
	public Authentication getAuthentication(HttpServletRequest request) {
		String fullToken = request.getHeader(JWT_HEADER_STRING);

		if (StringUtils.isBlank(fullToken)) {
			return null;
		}

		String jwt = StringUtils.replace(fullToken, JWT_PREFIX, "");
		Claims claims = getJwtClaims(jwt);
		if (claims == null) {
			return null;
		}

		String userId = claims.getSubject();

		if (claims.getExpiration().before(new Date())) {
			return null;
		}

		String authoritiesStr = claims.get(AUTHORITIES_KEY_NM).toString();

		Collection<? extends GrantedAuthority> authorities = Arrays.stream(authoritiesStr.split(",")).map(SimpleGrantedAuthority::new).collect(
			Collectors.toList());

		return new UsernamePasswordAuthenticationToken(userId, null, authorities);
	}

	private Claims getJwtClaims(String token) {

		try {
			return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();

		} catch (Exception e) {
			log.error("잘못된 토큰 요청 : {}", e.getMessage());
			return null;
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}
}
