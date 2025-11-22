package sopt.server.web3.domain.diary.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sopt.server.web3.domain.diary.entity.Diary;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

  /**
   * 커서 기반 페이징 - 첫 페이지 조회
   * diaryId 기준 내림차순 (최신순)
   */
  @Query("SELECT d FROM Diary d ORDER BY d.diaryId DESC")
  List<Diary> findAllWithCursor(Pageable pageable);

  /**
   * 커서 기반 페이징 - 다음 페이지 조회
   * 커서(diaryId)보다 작은 ID를 가진 다이어리 조회 (최신순)
   */
  @Query("SELECT d FROM Diary d WHERE d.diaryId < :cursor ORDER BY d.diaryId DESC")
  List<Diary> findAllWithCursor(@Param("cursor") Long cursor, Pageable pageable);
}
