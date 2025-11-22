package sopt.server.web3.domain.diary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sopt.server.web3.domain.diary.entity.Diary;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

  List<Diary> findAllByOrderByCreatedAtDesc();
}
