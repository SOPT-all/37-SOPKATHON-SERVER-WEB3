package sopt.server.web3.domain.openai.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * OpenAI 응답 생성기
 *
 * Spring AI를 사용하여 메시지를 전달받아 OpenAI API를 호출하고 응답을 반환합니다.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SimpleAIResponseGenerator {

	private final ChatModel chatModel;

	@Value("${spring.ai.openai.chat.options.model}")
	private String model;

	@Value("${spring.ai.openai.chat.options.temperature}")
	private Double temperature;

	@Value("${spring.ai.openai.chat.options.max-tokens}")
	private Integer maxTokens;

	@Value("${ai.system-prompt}")
	private String systemPrompt;

	/**
	 * 메시지에 대한 AI 응답 생성
	 *
	 * @param message 사용자 메시지
	 * @return AI 응답 내용
	 */
	public String generateResponse(String message) {
		try {
			log.info("AI 응답 생성 요청: {}", message);

			if (message == null || message.trim().isEmpty()) {
				throw new IllegalArgumentException("메시지가 비어있습니다.");
			}

			String response = callOpenAI(message.trim());

			log.info("AI 응답 생성 완료");
			return response;

		} catch (Exception e) {
			log.error("AI 응답 생성 실패: {}", e.getMessage(), e);
			throw new RuntimeException("AI 응답 생성에 실패했습니다.", e);
		}
	}

	/**
	 * OpenAI API 호출
	 */
	private String callOpenAI(String message) {
		String combinedPrompt = systemPrompt + "\n\n사용자: " + message;

		OpenAiChatOptions options = OpenAiChatOptions.builder()
			.withModel(model)
			.withTemperature(temperature)
			.withMaxTokens(maxTokens)
			.build();

		Prompt prompt = new Prompt(combinedPrompt, options);
		ChatResponse response = chatModel.call(prompt);

		return response.getResult().getOutput().getContent().trim();
	}
}
