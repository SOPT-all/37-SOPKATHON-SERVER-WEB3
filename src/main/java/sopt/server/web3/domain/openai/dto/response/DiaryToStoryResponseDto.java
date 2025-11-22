package sopt.server.web3.domain.openai.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "설화 변환 응답")
public class DiaryToStoryResponseDto {

	@Schema(description = "저장된 일기 ID")
	private Long diaryId;

	@Schema(description = "저장된 설화 ID")
	private Long sagaId;

	@Schema(description = "설화로 변환된 내용")
	private String story;
}
