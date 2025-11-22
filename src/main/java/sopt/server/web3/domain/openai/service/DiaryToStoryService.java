package sopt.server.web3.domain.openai.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sopt.server.web3.domain.openai.domain.Theme;
import sopt.server.web3.domain.openai.dto.response.DiaryToStoryResponseDto;
import sopt.server.web3.domain.openai.strategy.PromptStrategy;
import sopt.server.web3.domain.openai.strategy.PromptStrategyFactory;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiaryToStoryService {

	private final PromptStrategyFactory promptStrategyFactory;
	private final SimpleAIResponseGenerator aiResponseGenerator;

	/**
	 * 일기 내용을 주제에 맞는 설화로 변환합니다.
	 *
	 * @param theme 주제 (FAITH, LOVE, HOPE)
	 * @param diaryContent 일기 내용
	 * @return 설화로 변환된 내용
	 */
	public DiaryToStoryResponseDto convertDiaryToStory(Theme theme, String diaryContent) {
		log.info("일기 -> 설화 변환 시작: theme={}, diaryLength={}", theme, diaryContent.length());

		PromptStrategy strategy = promptStrategyFactory.getStrategy(theme);

		String prompt = strategy.generatePrompt(diaryContent);

		String story = aiResponseGenerator.generateResponse(prompt);

		log.info("일기 -> 설화 변환 완료");

		return DiaryToStoryResponseDto.builder()
			.story(story)
			.build();
	}
}
