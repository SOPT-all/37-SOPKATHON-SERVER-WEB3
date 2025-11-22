package sopt.server.web3.global.response.error;

import lombok.Getter;

@Getter
public enum ErrorCode implements ErrorType {

	// 공통 에러
	INVALID_INPUT("C001", "입력값이 올바르지 않습니다", 400),
	INVALID_FORMAT("C002", "데이터 형식이 올바르지 않습니다", 400),

	// User 에러
	USER_NOT_FOUND("U001", "사용자를 찾을 수 없습니다", 404),

	INTERNAL_SERVER_ERROR("C999", "서버 내부 오류가 발생했습니다", 500);
	INTERNAL_SERVER_ERROR("C999", "서버 내부 오류가 발생했습니다", 500),

	DIARY_NOT_FOUND("D001", "다이어리를 찾을 수 없습니다", 404);

	private final String code;
	private final String message;
	private final int status;

	ErrorCode(String code, String message, int status) {
		this.code = code;
		this.message = message;
		this.status = status;
	}
}
