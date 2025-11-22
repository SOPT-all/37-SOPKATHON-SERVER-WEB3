package sopt.server.web3.domain.diary.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import sopt.server.web3.domain.user.entity.UserSagaCount;

@Getter
@Builder
@Schema(description = "토끼풀 카운트 응답")
public class ShamrockCountResponseDto {
  @Schema(description = "유저 ID", example = "1")
  private Long userId;

  @Schema(description = "신뢰 설화 개수", example = "5")
  private int faithCount;

  @Schema(description = "희망 설화 개수", example = "3")
  private int hopeCount;

  @Schema(description = "사랑 설화 개수", example = "7")
  private int loveCount;

  public static ShamrockCountResponseDto from(UserSagaCount userSagaCount) {
    return ShamrockCountResponseDto.builder()
        .userId(userSagaCount.getUser().getUserId())
        .faithCount(userSagaCount.getFaithCount())
        .hopeCount(userSagaCount.getHopeCount())
        .loveCount(userSagaCount.getLoveCount())
        .build();
  }
}
