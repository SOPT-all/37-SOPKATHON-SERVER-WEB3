package sopt.server.web3.domain.diary.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import sopt.server.web3.domain.diary.dto.DiaryDetailResponseDto;
import sopt.server.web3.domain.diary.dto.DiaryResponseDto;
import sopt.server.web3.domain.diary.service.DiaryService;
import sopt.server.web3.global.response.CommonApiResponse;
import sopt.server.web3.global.response.success.SuccessCode;

@RestController
@RequestMapping("/api/diaries")
@RequiredArgsConstructor
@Tag(name = "Diary", description = "다이어리 목록 조회 API")
public class DiaryController {

	private final DiaryService diaryService;

	@Operation(summary = "다이어리 전체 리스트 조회", description = "모든 다이어리를 최신순으로 조회합니다")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "조회 성공"),
		@ApiResponse(responseCode = "500", description = "서버 오류",
			content = @Content(schema = @Schema(implementation = CommonApiResponse.class)))
	})
	@GetMapping
	public CommonApiResponse<List<DiaryResponseDto>> getAllDiaries() {
		List<DiaryResponseDto> diaries = diaryService.findAllDiaries();
		return CommonApiResponse.success(SuccessCode.SUCCESS, diaries);
	}

	@Operation(summary = "다이어리 상세 조회", description = "다이어리 ID로 상세 정보를 조회합니다")
	@ApiResponses({
		@ApiResponse(
			responseCode = "200",
			description = "조회 성공",
			content = @Content(schema = @Schema(implementation = CommonApiResponse.class))
		),
		@ApiResponse(
			responseCode = "404",
			description = "다이어리를 찾을 수 없음",
			content = @Content(schema = @Schema(implementation = CommonApiResponse.class))
		)
	})
	@GetMapping("/{diaryId}")
	public CommonApiResponse<DiaryDetailResponseDto> getDiary(
		@Parameter(description = "다이어리 ID", required = true)
		@PathVariable("diaryId") Long diaryId) {
		DiaryDetailResponseDto diary = diaryService.getDiary(diaryId);
		return CommonApiResponse.success(SuccessCode.SUCCESS, diary);
	}
}
