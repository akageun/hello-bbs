package kr.geun.o.common.security.service;

import kr.geun.o.app.user.model.UserAuthEntity;
import kr.geun.o.app.user.model.UserEntity;
import kr.geun.o.app.user.repository.UserAuthRepository;
import kr.geun.o.app.user.repository.UserRepository;
import kr.geun.o.common.constants.AuthorityCd;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleUserDetailsServiceTest {

	@MockBean
	private UserAuthRepository userAuthRepository;

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private SimpleUserDetailsService simpleUserDetailsService;

	@Test(expected = UsernameNotFoundException.class)
	public void 유저정보조회관련_계정정보없음() {
		//GIVEN(Preparation)
		final String mockUserId = "akageun";

		given(userRepository.findByUserId(mockUserId)).willReturn(null);

		//WHEN(Execution)
		simpleUserDetailsService.loadUserByUsername(mockUserId);
	}

	@Test(expected = UsernameNotFoundException.class)
	public void 유저정보조회관련_권한없음() {
		//GIVEN(Preparation)
		//@formatter:off
		final String mockUserId = "akageun";

		UserEntity mockDbInfo = UserEntity.builder()
			.userId(mockUserId)
			.passWd("test")
			.build();

		//WHEN(Execution)
		given(userRepository.findByUserId(mockUserId)).willReturn(mockDbInfo);
		given(userAuthRepository.findByUserId(mockUserId)).willReturn(Collections.emptyList());

		//@formatter:on

		simpleUserDetailsService.loadUserByUsername(mockUserId);
	}

	@Test
	public void 유저정보조회관련_성공() {
		//GIVEN(Preparation)
		//@formatter:off
		final String mockUserId = "akageun";

		UserEntity mockDbInfo = UserEntity.builder()
			.userId(mockUserId)
			.passWd("test")
			.build();

		List<UserAuthEntity> mockAuthList = Arrays.asList(UserAuthEntity.builder().authorityCd(AuthorityCd.USER.roleCd()).build());

		//WHEN(Execution)
		given(userRepository.findByUserId(mockUserId)).willReturn(mockDbInfo);
		given(userAuthRepository.findByUserId(mockUserId)).willReturn(mockAuthList);

		//@formatter:on

		UserDetails userDetails = simpleUserDetailsService.loadUserByUsername(mockUserId);

		assertNotNull(userDetails);
		assertEquals(mockDbInfo.getUserId(), userDetails.getUsername());
		assertFalse(userDetails.getAuthorities().isEmpty());
	}

}
