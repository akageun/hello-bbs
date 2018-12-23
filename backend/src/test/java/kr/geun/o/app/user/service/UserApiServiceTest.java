package kr.geun.o.app.user.service;

import kr.geun.o.app.user.exception.AlreadyUsernameException;
import kr.geun.o.app.user.model.UserEntity;
import kr.geun.o.app.user.repository.UserRepository;
import kr.geun.o.common.constants.AuthorityCd;
import kr.geun.o.common.utils.SecUtils;
import kr.geun.o.common.security.service.SimpleUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
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

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserApiService userApiService;

	private static String mockUserId;
	private static String mockPassWd;

	@BeforeClass
	public static void setUp() {
		mockUserId = "akageun";
		mockPassWd = "test1234";
	}

	@Test(expected = BadCredentialsException.class)
	public void 유저정보가져오기_서비스테스트_비밀번호실패() {
		GIVEN:
		{
			UserDetails userDetails = new User(mockUserId, mockPassWd,
				SecUtils.mapToGrantedAuthorities(Arrays.asList(AuthorityCd.USER.roleCd())));

			given(simpleUserDetailsService.loadUserByUsername(mockUserId)).willReturn(userDetails);
		}

		WHEN:
		{
			userApiService.getUserDetails(mockUserId, mockPassWd);
		}
	}

	@Test
	public void 유저정보가져오기_서비스테스트_성공() {
		GIVEN:
		{
			UserDetails userDetails = new User(mockUserId, passwordEncoder.encode(mockPassWd),
				SecUtils.mapToGrantedAuthorities(Arrays.asList(AuthorityCd.USER.roleCd())));

			given(simpleUserDetailsService.loadUserByUsername(mockUserId)).willReturn(userDetails);
		}

		UserDetails details;

		WHEN:
		{
			details = userApiService.getUserDetails(mockUserId, mockPassWd);
		}

		THEN:
		{
			assertNotNull(details);
			assertEquals(mockUserId, details.getUsername());
		}
	}

	@Test(expected = AlreadyUsernameException.class)
	public void 유저생성_이미_존재하는_유저() {

		//GIVEN(Preparation)
		GIVEN:
		{
			UserEntity mockDbInfo = UserEntity.builder().userId(mockUserId).passWd("test").build();

			given(userRepository.findByUserId(mockUserId)).willReturn(mockDbInfo);
		}

		WHEN:
		{
			userApiService.preCreateUser(mockUserId, mockPassWd, mockPassWd);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void 유저생성_패스워드잘못됨() {

		String confirmPassWd = mockPassWd + "1";

		GIVEN:
		{
			given(userRepository.findByUserId(mockUserId)).willReturn(null);
		}

		WHEN:
		{
			userApiService.preCreateUser(mockUserId, mockPassWd, confirmPassWd);
		}
	}

	@Test
	public void 유저생성_테스트() {
		GIVEN:
		{
		}

		WHEN:
		{
		}

		THEN:
		{
		}
	}
}
