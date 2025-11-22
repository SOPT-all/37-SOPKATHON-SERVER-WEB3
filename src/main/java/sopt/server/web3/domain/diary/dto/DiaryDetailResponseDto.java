package sopt.server.web3.domain.diary.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import sopt.server.web3.domain.diary.entity.Diary;

@Getter
@Builder
@Schema(description = "다이어리 상세 응답")
public class DiaryDetailResponseDto {

  @Schema(description = "다이어리 ID", example = "1")
  private Long diaryId;

  @Schema(description = "감정 잎 종류", example = "HOPE")
  private String leafType;

  @Schema(description = "일화 제목", example = "행복한 친구들과의 저녁 시간")
  private String title;

  @Schema(description = "사용자가 입력한 원본 일기", example = "오늘은 정말 행복한 하루였다...")
  private String originalContent;

  @Schema(description = "AI가 변환한 일화", example = "한 사람이 친구들과 함께 저녁을 먹으며...")
  private String sagaContent;

  @Schema(description = "입력 시간", example = "2025-11-23T14:30:00")
  private LocalDateTime createdAt;

  public static DiaryDetailResponseDto from(Diary diary, String sagaContent) {
    return DiaryDetailResponseDto.builder()
        .diaryId(diary.getDiaryId())
        .leafType(diary.getLeafType().name())
        .title(diary.getTitle())
        .originalContent(diary.getOriginalContent())
        .sagaContent(sagaContent)
        .createdAt(diary.getCreatedAt())
        .build();
  }
}
