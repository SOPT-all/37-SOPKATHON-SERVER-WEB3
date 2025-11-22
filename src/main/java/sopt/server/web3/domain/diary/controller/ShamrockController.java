package sopt.server.web3.domain.diary.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sopt.server.web3.domain.diary.dto.ShamrockCountResponseDto;
import sopt.server.web3.domain.diary.service.ShamrockService;
import sopt.server.web3.global.response.CommonApiResponse;
import sopt.server.web3.global.response.success.SuccessCode;

@RestController
@RequestMapping("/api/shamrock-count")
@RequiredArgsConstructor
@Tag(name = "Shamrock", description = "토끼풀 카운트 관련 API")
public class ShamrockController {

  private final ShamrockService shamrockService;

  @Operation(summary = "토끼풀 카운트 조회", description = "유저 ID로 해당 유저의 전체 설화 카운트를 조회합니다")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "조회 성공",
          content = @Content(schema = @Schema(implementation = CommonApiResponse.class))
      ),
      @ApiResponse(
          responseCode = "404",
          description = "유저의 카운트를 찾을 수 없음",
          content = @Content(schema = @Schema(implementation = CommonApiResponse.class))
      )
  })
  @GetMapping
  public CommonApiResponse<ShamrockCountResponseDto> getShamrockCount(
      @Parameter(description = "유저 ID", required = true)
      @RequestParam("userId") Long userId) {
    ShamrockCountResponseDto response = shamrockService.getShamrockCount(userId);
    return CommonApiResponse.success(SuccessCode.SUCCESS, response);
  }
}

