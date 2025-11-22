package sopt.server.web3.domain.diary.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sopt.server.web3.domain.diary.entity.Saga;

public interface SagaRepository extends JpaRepository<Saga, Long> {
  Optional<Saga> findByDiary_DiaryId(Long diaryId);
}
