package sopt.server.web3.domain.diary.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sopt.server.web3.domain.diary.dto.DiaryDetailResponseDto;
import sopt.server.web3.domain.diary.dto.DiaryPageResponseDto;
import sopt.server.web3.domain.diary.dto.DiaryResponseDto;
import sopt.server.web3.domain.diary.entity.Diary;
import sopt.server.web3.domain.diary.entity.LeafType;
import sopt.server.web3.domain.diary.entity.Saga;
import sopt.server.web3.domain.diary.repository.DiaryRepository;
import sopt.server.web3.domain.diary.repository.SagaRepository;
import sopt.server.web3.domain.diary.vo.SavedDiaryInfo;
import sopt.server.web3.domain.user.entity.User;
import sopt.server.web3.domain.user.entity.UserSagaCount;
import sopt.server.web3.domain.user.repository.UserRepository;
import sopt.server.web3.domain.user.repository.UserSagaCountRepository;
import sopt.server.web3.global.exception.BaseException;
import sopt.server.web3.global.response.error.ErrorCode;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final SagaRepository sagaRepository;
    private final UserRepository userRepository;
    private final UserSagaCountRepository userSagaCountRepository;


    /**
     * 커서 기반 페이징으로 다이어리 목록을 조회합니다.
     *
     * @param cursor 마지막으로 조회한 diaryId (null이면 첫 페이지)
     * @param size 조회할 개수 (기본 10개)
     * @return 다이어리 목록과 다음 커서 정보
     */
    public DiaryPageResponseDto findDiariesWithCursor(Long cursor, int size) {
        // size + 1개를 조회하여 hasNext 판단
        Pageable pageable = PageRequest.of(0, size + 1);

        List<Diary> diaries = cursor == null
                ? diaryRepository.findAllWithCursor(pageable)
                : diaryRepository.findAllWithCursor(cursor, pageable);

        // hasNext 판단 및 실제 반환할 데이터 분리
        boolean hasNext = diaries.size() > size;
        List<Diary> content = hasNext ? diaries.subList(0, size) : diaries;

        // DTO 변환
        List<DiaryResponseDto> diaryDtos = content.stream()
                .map(DiaryResponseDto::from)
                .collect(Collectors.toList());

        // 다음 커서 설정 (마지막 항목의 diaryId)
        Long nextCursor = hasNext && !content.isEmpty()
                ? content.get(content.size() - 1).getDiaryId()
                : null;

        return DiaryPageResponseDto.of(diaryDtos, nextCursor, hasNext);
    }

    public DiaryDetailResponseDto getDiary(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
            .orElseThrow(() -> new BaseException(ErrorCode.DIARY_NOT_FOUND));

        String sagaContent = sagaRepository.findByDiary_DiaryId(diaryId)
            .map(Saga::getSagaContent)
            .orElse(null);

        return DiaryDetailResponseDto.from(diary, sagaContent);
    }

    /**
     * 일기와 설화를 함께 저장합니다.
     *
     * @param userId 사용자 ID
     * @param title 일기 제목
     * @param content 일기 내용
     * @param createdAt 일기 생성 시간
     * @param leafType 주제
     * @param sagaContent 설화 내용
     * @return 저장된 일기 ID와 설화 ID
     */
    @Transactional
    public SavedDiaryInfo saveDiaryWithSaga(
            Long userId,
            String title,
            String content,
            LocalDateTime createdAt,
            LeafType leafType,
            String sagaContent) {

        User user = findUser(userId);
        Diary savedDiary = createAndSaveDiary(user, title, content, createdAt, leafType);
        Saga savedSaga = createAndSaveSaga(savedDiary, sagaContent);
        updateUserSagaCount(user, leafType);

        return new SavedDiaryInfo(savedDiary.getDiaryId(), savedSaga.getSagaId());
    }

    /**
     * 사용자와 UserSagaCount를 함께 조회합니다. (쿼리 최적화)
     */
    private User findUser(Long userId) {
        return userRepository.findByIdWithSagaCount(userId)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));
    }

    /**
     * 일기를 생성하고 저장합니다.
     */
    private Diary createAndSaveDiary(User user, String title, String content,
                                     LocalDateTime createdAt, LeafType leafType) {
        Diary diary = Diary.builder()
                .user(user)
                .leafType(leafType)
                .originalContent(content)
                .title(title)
                .createdAt(createdAt)
                .build();
        return diaryRepository.save(diary);
    }

    /**
     * 설화를 생성하고 저장합니다.
     */
    private Saga createAndSaveSaga(Diary diary, String sagaContent) {
        Saga saga = Saga.builder()
                .diary(diary)
                .sagaContent(sagaContent)
                .build();
        return sagaRepository.save(saga);
    }

    /**
     * 사용자의 설화 카운트를 업데이트합니다.
     */
    private void updateUserSagaCount(User user, LeafType leafType) {
        UserSagaCount userSagaCount = getOrCreateUserSagaCount(user);
        userSagaCount.incrementCount(leafType);
    }

    /**
     * UserSagaCount를 조회하거나 없으면 새로 생성합니다.
     * User를 fetch join으로 조회했으므로 추가 쿼리 없이 가져옵니다.
     */
    private UserSagaCount getOrCreateUserSagaCount(User user) {
        UserSagaCount userSagaCount = user.getUserSagaCount();

        if (userSagaCount == null) {
            userSagaCount = UserSagaCount.builder()
                    .user(user)
                    .faithCount(0)
                    .hopeCount(0)
                    .loveCount(0)
                    .build();
            return userSagaCountRepository.save(userSagaCount);
        }

        return userSagaCount;
    }

}
