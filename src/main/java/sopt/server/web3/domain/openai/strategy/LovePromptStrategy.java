package sopt.server.web3.domain.openai.strategy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LovePromptStrategy implements PromptStrategy {

	@Value("${ai.diary-to-story.love-prompt}")
	private String promptTemplate;

	@Override
	public String generatePrompt(String diaryContent) {
		return String.format(promptTemplate, diaryContent);
	}
}
