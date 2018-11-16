package kr.geun.o.app.user.repository;

import kr.geun.o.app.user.model.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 유저 - 권한 repo
 *
 * @author akageun
 */
public interface UserAuthRepository extends JpaRepository<UserAuthEntity, Long> {

	/**
	 * 유저 아이디로 가져오기
	 *
	 * @param userId
	 * @return
	 */
	List<String> findByUserId(String userId);
}
