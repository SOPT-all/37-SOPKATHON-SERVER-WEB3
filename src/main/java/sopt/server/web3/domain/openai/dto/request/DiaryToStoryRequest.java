package sopt.server.web3.domain.openai.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import sopt.server.web3.domain.openai.domain.Theme;

@Schema(description = "일기를 설화로 변환 요청")
public record DiaryToStoryRequest(
	@Schema(description = "주제 (FAITH, LOVE, HOPE)", example = "FAITH")
	Theme theme,

	@Schema(description = "일기 내용", example = "오늘은 힘든 일이 있었지만, 포기하지 않고 끝까지 해냈다.")
	String diaryContent
) {
}
