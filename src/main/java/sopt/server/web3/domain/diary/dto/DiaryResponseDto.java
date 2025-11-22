package sopt.server.web3.domain.diary.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import sopt.server.web3.domain.diary.entity.Diary;

@Getter
@Builder
@Schema(description = "다이어리 응답")
public class DiaryResponseDto {

  @Schema(description = "다이어리 ID", example = "1")
  private Long diaryId;

  @Schema(description = "감정 잎 종류", example = "FAITH")
  private String leafType;

  @Schema(description = "일화 제목", example = "아이의 첫 걸음마")
  private String title;

  @Schema(description = "입력 시간", example = "2025-11-23 14:30:00")
  private LocalDateTime createdAt;

  public static DiaryResponseDto from(Diary diary) {

    return DiaryResponseDto.builder()
        .diaryId(diary.getDiaryId())
        .leafType(diary.getLeafType().name())
        .title(diary.getTitle())
        .createdAt(diary.getCreatedAt())
        .build();
  }
}
