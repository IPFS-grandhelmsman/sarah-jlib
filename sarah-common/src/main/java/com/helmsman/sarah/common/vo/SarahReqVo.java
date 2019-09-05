package com.helmsman.sarah.common.vo;

/**
 * Request vo returned by external http service
 * @author yangjian
 * @since 2019-01-17 下午4:37.
 */
public class SarahReqVo {

	private Integer errno;
	private Object data;

	public Integer getErrno() {
		return errno;
	}

	public void setErrno(Integer errno) {
		this.errno = errno;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return this.errno == 0;
	}
}
