package sopt.server.web3.domain.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sopt.server.web3.domain.diary.entity.Saga;

public interface SagaRepository extends JpaRepository<Saga, Long> {
}
