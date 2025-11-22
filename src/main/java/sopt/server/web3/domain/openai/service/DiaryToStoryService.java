package sopt.server.web3.domain.openai.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sopt.server.web3.domain.diary.service.DiaryService;
import sopt.server.web3.domain.diary.vo.SavedDiaryInfo;
import sopt.server.web3.domain.openai.dto.request.DiaryToStoryRequestDto;
import sopt.server.web3.domain.openai.dto.response.DiaryToStoryResponseDto;
import sopt.server.web3.domain.openai.strategy.PromptStrategy;
import sopt.server.web3.domain.openai.strategy.PromptStrategyFactory;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiaryToStoryService {

	private final PromptStrategyFactory promptStrategyFactory;
	private final SimpleAIResponseGenerator aiResponseGenerator;
	private final DiaryService diaryService;

	/**
	 * 일기 내용을 주제에 맞는 설화로 변환하고 저장합니다.
	 *
	 * @param request 일기 변환 요청 DTO
	 * @return 설화로 변환된 내용
	 */
	public DiaryToStoryResponseDto convertDiaryToStory(DiaryToStoryRequestDto request) {
		Long userId = 1L; // 고정된 사용자 ID

		log.info("일기 -> 설화 변환 시작: userId={}, theme={}, diaryLength={}",
			userId, request.getTheme(), request.getContent().length());

		// 1. 설화 생성
		PromptStrategy strategy = promptStrategyFactory.getStrategy(request.getTheme());
		String prompt = strategy.generatePrompt(request.getContent());
		String story = aiResponseGenerator.generateResponse(prompt);

		// 2. Diary와 Saga 저장 (DiaryService에 위임)
		SavedDiaryInfo savedInfo = diaryService.saveDiaryWithSaga(
			userId,
			request.getTitle(),
			request.getContent(),
			request.getCreatedAt(),
			request.getTheme(),
			story
		);

		log.info("일기 -> 설화 변환 완료: diaryId={}, sagaId={}", savedInfo.diaryId(), savedInfo.sagaId());

		return DiaryToStoryResponseDto.builder()
			.diaryId(savedInfo.diaryId())
			.sagaId(savedInfo.sagaId())
			.story(story)
			.build();
	}
}
