package com.helmsman.sarah.common.vo;

import com.helmsman.sarah.common.enums.ErrorCode;

/**
 * Output vo for external service
 * @author yangjian
 * @since 2019-01-21 下午4:31.
 */
public class SarahResVo extends com.ppblock.common.vo.ResultVo {

	public SarahResVo(ErrorCode error) {
		this.setCode(error.getCode());
		this.setMessage(error.getMessage());
	}

	public static SarahResVo success() {
		return instance(ErrorCode.SUCCESS);
	}

	public static SarahResVo success(Object data) {
		SarahResVo instance = instance(ErrorCode.SUCCESS);
		instance.setData(data);
		return instance;
	}

	public static SarahResVo fail() {
		return instance(ErrorCode.FAIL);
	}

	public static SarahResVo instance(ErrorCode error) {
		return new SarahResVo(error);
	}
}
