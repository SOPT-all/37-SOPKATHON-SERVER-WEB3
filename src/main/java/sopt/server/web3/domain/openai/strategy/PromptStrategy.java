package sopt.server.web3.domain.openai.strategy;

public interface PromptStrategy {

	/**
	 * 일기 내용을 기반으로 설화 생성을 위한 프롬프트를 생성합니다.
	 *
	 * @param diaryContent 사용자의 일기 내용
	 * @return OpenAI에 전달할 프롬프트
	 */
	String generatePrompt(String diaryContent);
}
