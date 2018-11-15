package kr.geun.o.app.user.controller.api;

import kr.geun.o.app.user.dto.UserDTO;
import kr.geun.o.app.user.model.UserAuthEntity;
import kr.geun.o.app.user.model.UserEntity;
import kr.geun.o.app.user.repository.UserAuthRepository;
import kr.geun.o.app.user.repository.UserRepository;
import kr.geun.o.app.user.service.UserApiService;
import kr.geun.o.common.utils.CmnUtils;
import kr.geun.o.config.security.jwt.JwtProvider;
import kr.geun.o.config.security.service.SimpleUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
	private UserAuthRepository userAuthRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserApiService userApiService;

	@RequestMapping("/user/v1/login")
	public ResponseEntity<String> userLogin(@Valid UserDTO.Login param, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(CmnUtils.getErrMsg(result, '\n'), HttpStatus.BAD_REQUEST);
		}

		try {
			UserDetails userDetails = userApiService.getUserDetails(param.getUserId(), param.getPassWd());

			return ResponseEntity.ok().body(userApiService.generatorToken(userDetails));

		} catch (Exception e) { //TODO : 익셉션 쪼개서 처리해야함.
			return null;
		}

	}

	@PostMapping("/user/v1/signup")
	public ResponseEntity<String> signup(@Valid UserDTO.SignUp param, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(CmnUtils.getErrMsg(result, '\n'), HttpStatus.BAD_REQUEST);
		}

//		UserEntity userEntityParam = UserEntity.builder().userId(param.getUserId()).passWd(passwordEncoder.encode(param.getPassWd())).build();
		//
		//		userRepository.save(userEntityParam);
		//
		//		UserAuthEntity userAuthEntityParam = UserAuthEntity.builder().userId(param.getUserId()).authorityCd("NORMAL").build();
		//
		//		userAuthRepository.save(userAuthEntityParam);

		return new ResponseEntity<>("성공", HttpStatus.OK);

	}
}
