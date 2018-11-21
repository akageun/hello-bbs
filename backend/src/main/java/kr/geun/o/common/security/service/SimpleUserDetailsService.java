package kr.geun.o.common.security.service;

import kr.geun.o.app.user.model.UserAuthEntity;
import kr.geun.o.app.user.model.UserEntity;
import kr.geun.o.app.user.repository.UserAuthRepository;
import kr.geun.o.app.user.repository.UserRepository;
import kr.geun.o.common.utils.SecUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 *
 * @author akageun
 */
@Slf4j
@Component
public class SimpleUserDetailsService implements UserDetailsService {

	@Autowired
	private UserAuthRepository userAuthRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

		UserEntity dbUserInfo = userRepository.findByUserId(userId);
		if (dbUserInfo == null) {
			throw new UsernameNotFoundException("NOT Found User");
		}

		List<UserAuthEntity> authList = userAuthRepository.findByUserId(userId);
		if (authList.isEmpty()) {
			throw new UsernameNotFoundException("계정 내 권한이 없습니다.");
		}

		return new User(dbUserInfo.getUserId(), dbUserInfo.getPassWd(),
			SecUtils.mapToGrantedAuthorities(authList.stream().map(userAuthEntity -> userAuthEntity.getAuthorityCd()).collect(Collectors.toList())));
	}

}
