package kr.geun.o.app.user.service;

import kr.geun.o.app.user.exception.AlreadyUsernameException;
import kr.geun.o.app.user.model.UserAuthEntity;
import kr.geun.o.app.user.model.UserEntity;
import kr.geun.o.app.user.repository.UserAuthRepository;
import kr.geun.o.app.user.repository.UserRepository;
import kr.geun.o.core.constants.AuthorityCd;
import kr.geun.o.core.security.jwt.JwtProvider;
import kr.geun.o.core.security.service.SimpleUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 유저 API 관련 service
 *
 * @author akageun
 */
@Slf4j
@Service
public class UserApiService {

    @Autowired
    private SimpleUserDetailsService simpleUserDetailsService;

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private UserRepository userRepository;

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
    public UserDetails getUserDetails(String userId, String passWd) {

        UserDetails userInfo = simpleUserDetailsService.loadUserByUsername(userId); //유저정보가 없을경우 UsernameNotFoundException 발생
        if (passwordEncoder.matches(passWd, userInfo.getPassword()) == false) { //비밀번호 체크
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
    public String generatorToken(UserDetails details) {
        return jwtProvider.generatorToken(new UsernamePasswordAuthenticationToken(details, details.getPassword(), details.getAuthorities()));
    }

    /**
     * 유저 생성
     * - 전처리
     *
     * @param userId
     * @param passWd
     * @param confirmPassWd
     */
    public void preCreateUser(String userId, String passWd, String confirmPassWd) {

        UserEntity dbInfo = userRepository.findByUserId(userId);
        if (dbInfo != null) {
            throw new AlreadyUsernameException("이미 등록된 유저 입니다.");
        }

        //TODO : 비밀번호 정규식 추가, 어노테이션으로?

        if (StringUtils.equals(passWd, confirmPassWd) == false) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    /**
     * 유저 생성
     *
     * @param userId
     * @param passWd
     */
    @Transactional
    public void createUser(String userId, String passWd) {

        String encodePassWd = passwordEncoder.encode(passWd);

        UserEntity userEntityParam = UserEntity.builder().userId(userId).passWd(encodePassWd).build();

        userRepository.save(userEntityParam);

        UserAuthEntity userAuthEntityParam = UserAuthEntity.builder().userId(userId).authorityCd(AuthorityCd.USER.roleCd()).build();

        userAuthRepository.save(userAuthEntityParam);

    }
}
