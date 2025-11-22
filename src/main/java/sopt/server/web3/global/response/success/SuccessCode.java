package sopt.server.web3.global.response.success;

import lombok.Getter;

@Getter
public enum SuccessCode implements SuccessType {
	// 공통 응답 코드
	SUCCESS("S200", "성공"),

	// OpenAI 응답 코드
	OPENAI_CHAT_SUCCESS("O001", "채팅 응답 생성 성공"),
	OPENAI_DIARY_TO_STORY_SUCCESS("O002", "일기를 설화로 변환 성공");

	private final String code;
	private final String message;

	SuccessCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
