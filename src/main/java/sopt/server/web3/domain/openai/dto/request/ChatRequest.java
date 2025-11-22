package sopt.server.web3.domain.openai.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "채팅 요청")
public record ChatRequest(
	@Schema(description = "사용자 메시지", example = "안녕하세요!")
	String message
) {
}
