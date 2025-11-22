package sopt.server.web3.domain.diary.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "다이어리 목록 커서 페이징 응답")
public class DiaryPageResponseDto {

    @Schema(description = "다이어리 목록")
    private List<DiaryResponseDto> diaries;

    @Schema(description = "다음 페이지 커서 (마지막 diaryId)", example = "15")
    private Long nextCursor;

    @Schema(description = "다음 페이지 존재 여부", example = "true")
    private boolean hasNext;

    @Schema(description = "전체 다이어리 개수", example = "42")
    private long totalCount;

    @Builder
    private DiaryPageResponseDto(List<DiaryResponseDto> diaries, Long nextCursor, boolean hasNext, long totalCount) {
        this.diaries = diaries;
        this.nextCursor = nextCursor;
        this.hasNext = hasNext;
        this.totalCount = totalCount;
    }

    public static DiaryPageResponseDto of(List<DiaryResponseDto> diaries, Long nextCursor, boolean hasNext, long totalCount) {
        return DiaryPageResponseDto.builder()
                .diaries(diaries)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .totalCount(totalCount)
                .build();
    }
}
