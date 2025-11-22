package sopt.server.web3.domain.diary.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sopt.server.web3.domain.diary.dto.DiaryResponseDto;
import sopt.server.web3.domain.diary.repository.DiaryRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public List<DiaryResponseDto> findAllDiaries() {
        return diaryRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(DiaryResponseDto::from)
                .collect(Collectors.toList());
    }
}
