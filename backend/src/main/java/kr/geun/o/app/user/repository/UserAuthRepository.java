package kr.geun.o.app.user.repository;

import kr.geun.o.app.user.model.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 *
 * @author akageun
 */
public interface UserAuthRepository extends JpaRepository<UserAuthEntity, Long> {

	List<String> findByUserId(String userId);
}
