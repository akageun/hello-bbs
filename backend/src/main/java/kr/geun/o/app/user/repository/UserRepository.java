package kr.geun.o.app.user.repository;

import kr.geun.o.app.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 유저 repo
 *
 * @author akageun
 */
public interface UserRepository extends JpaRepository<UserEntity, String> {

	UserEntity findByUserId(String userId);
}
