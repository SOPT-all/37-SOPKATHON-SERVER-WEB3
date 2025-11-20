package sopt.server.web3.global.exception;

import lombok.Getter;
import sopt.server.web3.global.response.error.ErrorCode;
import sopt.server.web3.global.response.error.ErrorType;

@Getter
public class BaseException extends RuntimeException {
	private final ErrorType errorCode;

	public BaseException(ErrorType errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public BaseException(ErrorCode errorCode, String detail) {
		super(errorCode.getMessage() + " → " + detail);
		this.errorCode = errorCode;
	}
}
