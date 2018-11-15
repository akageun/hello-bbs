package kr.geun.o.config.security.jwt.filter;

import kr.geun.o.config.security.jwt.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtAuthenticationFilterTest {

    @MockBean
    private JwtProvider jwtProvider;

    @Test
    public void 필터테스트_성공() throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();

        given(jwtProvider.getAuthentication(mockRequest)).willReturn(
            new UsernamePasswordAuthenticationToken("akageun", null, Collections.emptyList()));

        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtProvider);

        // create the objects to be mocked

        MockHttpServletResponse mockResponse = new MockHttpServletResponse();

        MockFilterChain filterChain = new MockFilterChain();
        filter.doFilter(mockRequest, mockResponse, filterChain);

        assertEquals("akageun", SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Test
    public void 필터테스트_로그인정보가_없을경우() throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();

        given(jwtProvider.getAuthentication(mockRequest)).willReturn(null);

        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtProvider);

        // create the objects to be mocked

        MockHttpServletResponse mockResponse = new MockHttpServletResponse();

//        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
//        securityContextLogoutHandler.logout(mockRequest, mockResponse, null);

        MockFilterChain filterChain = new MockFilterChain();
        filter.doFilter(mockRequest, mockResponse, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

}
