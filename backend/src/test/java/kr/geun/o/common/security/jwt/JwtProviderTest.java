package kr.geun.o.common.security.jwt;

import io.jsonwebtoken.MalformedJwtException;
import kr.geun.o.common.constants.AuthorityCd;
import kr.geun.o.common.utils.SecUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtProviderTest {

	@Autowired
	private JwtProvider jwtProvider;

	private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;

	private static final String MOCK_USER_ID = "akageun";
	private static final String MOCK_PASS_WD = "test1234";

	@Before
	public void init() {
		UserDetails mockUserDetails = new User(MOCK_USER_ID, MOCK_PASS_WD,
			SecUtils.mapToGrantedAuthorities(Arrays.asList(AuthorityCd.USER.roleCd())));

		usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(mockUserDetails, mockUserDetails.getPassword(),
			mockUserDetails.getAuthorities());
	}

	@Test
	public void 토큰_생성하기_성공() {
		String token = jwtProvider.generatorToken(usernamePasswordAuthenticationToken);
		assertNotNull(token);
	}

	@Test
	public void 토큰꺼내서_파싱하기_성공() {
		String mockToken = jwtProvider.generatorToken(usernamePasswordAuthenticationToken);

		MockHttpServletRequest mockRequest = new MockHttpServletRequest();
		mockRequest.addHeader("Authorization", mockToken);

		Authentication authentication = jwtProvider.getAuthentication(mockRequest);

		assertNotNull(authentication);
		assertEquals(MOCK_USER_ID, authentication.getName());
		assertNull(authentication.getCredentials());

	}

	@Test
	public void 토큰꺼내서_널테스트() {
		MockHttpServletRequest mockRequest = new MockHttpServletRequest();

		Authentication authentication = jwtProvider.getAuthentication(mockRequest);

		assertNull(authentication);

	}

	@Test(expected = MalformedJwtException.class)
	public void 토큰꺼내서_파싱에러_테스트() {
		MockHttpServletRequest mockRequest = new MockHttpServletRequest();
		mockRequest.addHeader("Authorization", "test.token.test");

		Authentication authentication = jwtProvider.getAuthentication(mockRequest);

		assertNull(authentication);

	}
}
