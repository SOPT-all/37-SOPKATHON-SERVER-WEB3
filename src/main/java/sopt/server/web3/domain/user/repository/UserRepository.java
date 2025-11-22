package sopt.server.web3.domain.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sopt.server.web3.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * User와 UserSagaCount를 함께 조회합니다. (N+1 문제 방지)
     */
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.userSagaCount WHERE u.userId = :userId")
    Optional<User> findByIdWithSagaCount(@Param("userId") Long userId);
}
