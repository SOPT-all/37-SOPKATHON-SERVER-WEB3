package sopt.server.web3.domain.diary.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sopt.server.web3.domain.diary.dto.DiaryDetailResponseDto;
import sopt.server.web3.domain.diary.dto.DiaryResponseDto;
import sopt.server.web3.domain.diary.entity.Diary;
import sopt.server.web3.domain.diary.entity.Saga;
import sopt.server.web3.domain.diary.repository.DiaryRepository;
import sopt.server.web3.domain.diary.repository.SagaRepository;
import sopt.server.web3.global.exception.BaseException;
import sopt.server.web3.global.response.error.ErrorCode;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final SagaRepository sagaRepository;

    public List<DiaryResponseDto> findAllDiaries() {
        return diaryRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(DiaryResponseDto::from)
                .collect(Collectors.toList());
    }

    public DiaryDetailResponseDto getDiary(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
            .orElseThrow(() -> new BaseException(ErrorCode.DIARY_NOT_FOUND));

        String sagaContent = sagaRepository.findByDiary_DiaryId(diaryId)
            .map(Saga::getSagaContent)
            .orElse(null);

        return DiaryDetailResponseDto.from(diary, sagaContent);
    }
}
