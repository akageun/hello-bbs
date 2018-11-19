package kr.geun.o;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication  {

//	@Autowired
//	private UserAuthRepository userAuthRepository;
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Autowired
//	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		UserEntity userEntityParam = UserEntity.builder().userId("test").passWd(passwordEncoder.encode("test")).build();
//
//		userRepository.save(userEntityParam);
//
//		UserAuthEntity userAuthEntityParam = UserAuthEntity.builder().userId("test").authorityCd("ROLE_USER").build();
//
//		userAuthRepository.save(userAuthEntityParam);
//	}
}
