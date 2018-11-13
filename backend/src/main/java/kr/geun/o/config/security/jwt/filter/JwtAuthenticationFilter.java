package kr.geun.o.config.security.jwt.filter;

import kr.geun.o.config.security.jwt.JwtProvider;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
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

		Authentication authentication = jwtProvider.getAuthentication((HttpServletRequest) servletRequest);

		//TODO : 만료여부 체크

		if (authentication != null) {
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}
}
