package sopt.server.web3.domain.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import sopt.server.web3.domain.user.entity.UserSagaCount;

public interface UserSagaCountRepository extends JpaRepository<UserSagaCount, Long> {
    Optional<UserSagaCount> findByUser_UserId(Long userId);
}
