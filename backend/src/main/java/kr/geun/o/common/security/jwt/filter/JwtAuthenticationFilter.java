package kr.geun.o.common.security.jwt.filter;

import io.jsonwebtoken.ExpiredJwtException;
import kr.geun.o.common.security.jwt.JwtProvider;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *
 *
 * @author akageun
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtAuthenticationFilter extends GenericFilterBean {

	private JwtProvider jwtProvider;

	public JwtAuthenticationFilter(JwtProvider jwtProvider) {
		this.jwtProvider = jwtProvider;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
		IOException,
		ServletException {
		try {
			Authentication authentication = jwtProvider.getAuthentication((HttpServletRequest) servletRequest);

			if (authentication != null) {
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}

		} catch (ExpiredJwtException e) {
			log.error("e : {}, {}", e.getMessage(), e);
			SecurityContextHolder.clearContext();
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}
}
