package kr.geun.o.config;

import kr.geun.o.app.user.model.UserAuthEntity;
import kr.geun.o.app.user.model.UserEntity;
import kr.geun.o.app.user.repository.UserAuthRepository;
import kr.geun.o.app.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author akageun
 */
@Component
public class TempApplicationRunner implements CommandLineRunner {
	@Autowired
	private UserAuthRepository userAuthRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		UserEntity userEntityParam = UserEntity.builder().userId("test").passWd(passwordEncoder.encode("test")).build();
		userRepository.save(userEntityParam);

		UserAuthEntity userAuthEntityParam = UserAuthEntity.builder().userId("test").authorityCd("ROLE_USER").build();
		userAuthRepository.save(userAuthEntityParam);
	}
}
