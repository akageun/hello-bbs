package kr.geun.o.app.user.controller.api;

import kr.geun.o.app.user.dto.UserDTO;
import kr.geun.o.app.user.service.UserApiService;
import kr.geun.o.common.utils.CmnUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
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
@RequestMapping("/api/user/v1")
public class UserApiController {

	@Autowired
	private UserApiService userApiService;

	@PostMapping("/login")
	public ResponseEntity<String> userLogin(@Valid UserDTO.Login param, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(CmnUtils.getErrMsg(result, '\n'), HttpStatus.BAD_REQUEST);
		}

		try {
			UserDetails userDetails = userApiService.getUserDetails(param.getUserId(), param.getPassWd());

			String token = userApiService.generatorToken(userDetails);

			return ResponseEntity.ok().body(token);

		} catch (Exception e) { //TODO : 익셉션 쪼개서 처리해야함.
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("에러발생");
		}

	}

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@Valid UserDTO.SignUp param, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(CmnUtils.getErrMsg(result, '\n'), HttpStatus.BAD_REQUEST);
		}

		try {
			userApiService.preCreateUser(param.getUserId(), param.getPassWd(), param.getConfirmPassWd());
			userApiService.createUser(param.getUserId(), param.getPassWd());

			return ResponseEntity.ok().body("성공");

		} catch (Exception e) { //TODO : 익셉션 쪼개서 처리해야함.
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("에러발생");
		}
	}
}
