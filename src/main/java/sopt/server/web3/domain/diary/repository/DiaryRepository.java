package sopt.server.web3.domain.diary.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sopt.server.web3.domain.diary.entity.Diary;
import sopt.server.web3.domain.diary.entity.LeafType;

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

  /**
   * 테마별 커서 기반 페이징 - 첫 페이지 조회
   * 특정 LeafType으로 필터링하여 최신순으로 조회
   */
  @Query("SELECT d FROM Diary d WHERE d.leafType = :leafType ORDER BY d.diaryId DESC")
  List<Diary> findByLeafTypeWithCursor(@Param("leafType") LeafType leafType, Pageable pageable);

  /**
   * 테마별 커서 기반 페이징 - 다음 페이지 조회
   * 특정 LeafType으로 필터링하고 커서보다 작은 ID를 가진 다이어리 조회 (최신순)
   */
  @Query("SELECT d FROM Diary d WHERE d.leafType = :leafType AND d.diaryId < :cursor ORDER BY d.diaryId DESC")
  List<Diary> findByLeafTypeWithCursor(@Param("leafType") LeafType leafType, @Param("cursor") Long cursor, Pageable pageable);

  /**
   * 전체 다이어리 개수 조회
   */
  @Query("SELECT COUNT(d) FROM Diary d")
  long countAllDiaries();

  /**
   * 테마별 다이어리 개수 조회
   */
  @Query("SELECT COUNT(d) FROM Diary d WHERE d.leafType = :leafType")
  long countByLeafType(@Param("leafType") LeafType leafType);
}
