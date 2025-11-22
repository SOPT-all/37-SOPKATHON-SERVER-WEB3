package sopt.server.web3.domain.diary.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sopt.server.web3.domain.diary.dto.DiaryDetailResponseDto;
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
     * 사용자를 조회합니다.
     */
    private User findUser(Long userId) {
        return userRepository.findById(userId)
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
     */
    private UserSagaCount getOrCreateUserSagaCount(User user) {
        return userSagaCountRepository.findByUser_UserId(user.getUserId())
                .orElseGet(() -> {
                    UserSagaCount newCount = UserSagaCount.builder()
                            .user(user)
                            .faithCount(0)
                            .hopeCount(0)
                            .loveCount(0)
                            .build();
                    return userSagaCountRepository.save(newCount);
                });
    }

}
