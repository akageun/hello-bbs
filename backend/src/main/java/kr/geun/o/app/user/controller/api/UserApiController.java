package kr.geun.o.app.user.controller.api;

import kr.geun.o.config.security.jwt.JwtProvider;
import kr.geun.o.config.security.service.SimpleUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author akageun
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class UserApiController {

	@Autowired
	private SimpleUserDetailsService simpleUserDetailsService;

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping("/user/v1/signin")
	public String getUserInfo(String userId, String passwd) {

		UserDetails userInfo = simpleUserDetailsService.loadUserByUsername(userId);
		if (passwordEncoder.matches(passwd, userInfo.getPassword()) == false) { //비밀번호 체크
			return "실패";
		}

		return jwtProvider.generatorToken(new UsernamePasswordAuthenticationToken(userInfo, userInfo.getPassword(), userInfo.getAuthorities()));
	}
}
