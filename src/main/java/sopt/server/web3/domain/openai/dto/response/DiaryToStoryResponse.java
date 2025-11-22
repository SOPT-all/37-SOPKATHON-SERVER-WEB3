package sopt.server.web3.domain.openai.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "설화 변환 응답")
public record DiaryToStoryResponse(
	@Schema(description = "설화로 변환된 내용")
	String story
) {
}
