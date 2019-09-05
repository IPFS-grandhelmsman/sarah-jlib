package com.helmsman.sarah.node.exception;

import com.helmsman.sarah.common.enums.ErrorCode;

/**
 * @author yangjian
 * @since 2019-01-21 下午4:09.
 */
public class AppException extends RuntimeException {

	private String code;

	public AppException(ErrorCode error) {
		super(error.getMessage());
		this.code = error.getCode();
	}

	public AppException(String code, String message) {
		super(message);
		this.code = code;
	}

	public AppException(String message) {
		super(message);
		this.code = ErrorCode.FAIL.getCode();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
