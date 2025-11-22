package sopt.server.web3.domain.openai.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sopt.server.web3.domain.openai.domain.Theme;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "일기를 설화로 변환 요청")
public class DiaryToStoryRequestDto {

	@Schema(description = "주제 (FAITH, LOVE, HOPE)", example = "FAITH")
	private Theme theme;

	@Schema(description = "일기 제목", example = "힘든 하루를 이겨낸 날")
	private String title;

	@Schema(description = "일기 내용", example = "오늘은 힘든 일이 있었지만, 포기하지 않고 끝까지 해냈다.")
	private String diaryContent;
}
