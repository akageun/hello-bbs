package kr.geun.o.config.security;

import kr.geun.o.common.constants.AuthorityCd;
import kr.geun.o.config.security.jwt.JwtProvider;
import kr.geun.o.config.security.jwt.filter.JwtAuthenticationFilter;
import kr.geun.o.config.security.jwt.impl.JwtProviderImpl;
import kr.geun.o.config.security.service.SimpleUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 *
 * @author akageun
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SimpleUserDetailsService simpleUserDetailsService;

	@Bean
	public JwtProvider jwtProvider() {
		return new JwtProviderImpl();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//@formatter:off
		http
			.addFilterBefore(new JwtAuthenticationFilter(jwtProvider()),
					UsernamePasswordAuthenticationFilter.class)
			.cors()
			.and()
			.csrf()
				.disable()
			.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/api/user/**")
					.permitAll()
				.antMatchers("/api/bbs/**")
					.hasAnyRole(AuthorityCd.USER.name(), AuthorityCd.ADMIN.name())
				.antMatchers("/api/admin/**")
					.hasAnyRole(AuthorityCd.ADMIN.name())
			.anyRequest()
				.authenticated()
					;
		//@formatter:on

		//super.configure(http);
	}

	@Override
	protected UserDetailsService userDetailsService() {
		return simpleUserDetailsService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
