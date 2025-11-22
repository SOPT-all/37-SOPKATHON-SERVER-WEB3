package sopt.server.web3.domain.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import sopt.server.web3.domain.diary.entity.Saga;

public interface SagaRepository extends JpaRepository<Saga, Long> {
  Optional<Saga> findByDiary_DiaryId(Long diaryId);
}
