package sopt.server.web3.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sopt.server.web3.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
