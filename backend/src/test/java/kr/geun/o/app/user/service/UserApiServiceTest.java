package kr.geun.o.app.user.service;

import kr.geun.o.common.utils.SecUtils;
import kr.geun.o.config.security.service.SimpleUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApiServiceTest {

	@MockBean
	private SimpleUserDetailsService simpleUserDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserApiService userApiService;

	@Test(expected = BadCredentialsException.class)
	public void 유저정보가져오기_서비스테스트_비밀번호실패() {
		//GIVEN(Preparation)
		final String mockUserId = "akageun";
		final String mockPassWd = "test1234";

		UserDetails userDetails = new User(mockUserId, mockPassWd, SecUtils.mapToGrantedAuthorities(Arrays.asList("NORMAL")));

		given(simpleUserDetailsService.loadUserByUsername(mockUserId)).willReturn(userDetails);

		//WHEN(Execution)
		UserDetails details = userApiService.getUserDetails(mockUserId, mockPassWd);

	}

	@Test
	public void 유저정보가져오기_서비스테스트_성공() {
		//GIVEN(Preparation)
		final String mockUserId = "akageun";
		final String mockPassWd = "test1234";

		UserDetails userDetails = new User(mockUserId, passwordEncoder.encode(mockPassWd), SecUtils.mapToGrantedAuthorities(Arrays.asList("NORMAL")));

		given(simpleUserDetailsService.loadUserByUsername(mockUserId)).willReturn(userDetails);

		//WHEN(Execution)
		UserDetails details = userApiService.getUserDetails(mockUserId, mockPassWd);

		//THEN(Verification)
		assertNotNull(details);
		assertEquals(mockUserId, details.getUsername());

	}

//	@Test
//	public void generatorToken() {
//	}
}