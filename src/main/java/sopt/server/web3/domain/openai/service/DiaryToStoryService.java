package sopt.server.web3.domain.openai.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sopt.server.web3.domain.openai.domain.Theme;
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
	public String convertDiaryToStory(Theme theme, String diaryContent) {
		log.info("일기 -> 설화 변환 시작: theme={}, diaryLength={}", theme, diaryContent.length());

		// 주제에 맞는 전략 선택
		PromptStrategy strategy = promptStrategyFactory.getStrategy(theme);

		// 전략으로 프롬프트 생성
		String prompt = strategy.generatePrompt(diaryContent);

		// OpenAI 호출하여 설화 생성
		String story = aiResponseGenerator.generateResponse(prompt);

		log.info("일기 -> 설화 변환 완료");
		return story;
	}
}
