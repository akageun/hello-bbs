package kr.geun.o.app.user.repository;

import kr.geun.o.app.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 *
 * @author akageun
 */
public interface UserRepository extends JpaRepository<UserEntity, String> {
}
