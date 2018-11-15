package kr.geun.o.app.user.service.impl;

import kr.geun.o.app.user.service.UserApiService;
import kr.geun.o.config.security.jwt.JwtProvider;
import kr.geun.o.config.security.service.SimpleUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 *
 * @author akageun
 */
@Slf4j
@Service
public class UserApiServiceImpl implements UserApiService {

	@Autowired
	private SimpleUserDetailsService simpleUserDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtProvider jwtProvider;

	/**
	 * 유저 정보 가져오기
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public UserDetails getUserDetails(String userId, String passWd) {

		UserDetails userInfo = simpleUserDetailsService.loadUserByUsername(userId); //유저정보가 없을경우 UsernameNotFoundException 발생
		if (passwordEncoder.matches(userId, passWd) == false) { //비밀번호 체크
			throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
		}

		return userInfo;
	}

	/**
	 * 토큰 생성
	 *
	 * @param details
	 * @return
	 */
	@Override
	public String generatorToken(UserDetails details) {
		return jwtProvider.generatorToken(new UsernamePasswordAuthenticationToken(details, details.getPassword(), details.getAuthorities()));
	}
}
