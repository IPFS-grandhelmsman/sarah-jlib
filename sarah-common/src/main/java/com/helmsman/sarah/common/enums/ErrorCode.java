package com.helmsman.sarah.common.enums;

/**
 * 错误代码
 * @author yangjian
 * @since 2018-11-22 下午6:57.
 */
public enum ErrorCode {
	SUCCESS("000", "操作成功."),
	FAIL("001", "系统开了小差."),
	INVALID_ARGS("002", "参数错误."),
	NO_RECORDS("003", "没有找到记录."),
	UN_AUTHORIZED("401", "你没有操作权限.")

	// error codes for sarah-node

	// error codes for sarah-gateway
	;

	ErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	private String code;
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
