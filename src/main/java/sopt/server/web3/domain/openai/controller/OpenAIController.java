package sopt.server.web3.domain.openai.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import sopt.server.web3.domain.openai.dto.request.DiaryToStoryRequestDto;
import sopt.server.web3.domain.openai.dto.response.DiaryToStoryResponseDto;
import sopt.server.web3.domain.openai.service.DiaryToStoryService;
import sopt.server.web3.global.response.CommonApiResponse;
import sopt.server.web3.global.response.success.SuccessCode;

@Tag(name = "OpenAI", description = "OpenAI API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OpenAIController {

	private final DiaryToStoryService diaryToStoryService;

	@Operation(summary = "일기를 설화로 변환", description = "주제(Faith, Love, Hope)에 맞춰 일기 내용을 설화로 변환합니다.")
	@PostMapping("/diary")
	public CommonApiResponse<DiaryToStoryResponseDto> convertDiaryToStory(@RequestBody DiaryToStoryRequestDto request) {

		DiaryToStoryResponseDto response = diaryToStoryService.convertDiaryToStory(request.getTheme(), request.getDiaryContent());

		return CommonApiResponse.success(SuccessCode.OPENAI_DIARY_TO_STORY_SUCCESS, response);
	}
}
