package com.helmsman.sarah.node.exception;

import com.helmsman.sarah.common.enums.ErrorCode;

/**
 * @author yangjian
 * @since 2019-01-21 下午4:11.
 */
public class UnAuthorizedException extends AppException {


	public UnAuthorizedException(ErrorCode error) {
		super(error);
	}

	public UnAuthorizedException(String messgae) {
		super(messgae);
		this.setCode(ErrorCode.UN_AUTHORIZED.getCode());
	}
}
