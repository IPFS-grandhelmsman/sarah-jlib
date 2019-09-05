package com.helmsman.sarah.node.model;

/**
 * @author yangjian
 * @since 2019-01-17 下午2:27.
 */
public class AlertEntry {

	/**
	 * alert types constants
	 */
	public static final int ALERT_TYPE_LOADAVG = 1;
	public static final int ALERT_TYPE_RAM = 2;
	public static final int ALERT_TYPE_DISK = 3;
	public static final int ALERT_TYPE_INCOMING_BD = 4;
	public static final int ALERT_TYPE_OUTGOING_BD = 5;
	public static final int ALERT_TYPE_FAULT = 6;

	private Integer userId;
	private Long nodeId;
	private Integer alertType;
	private Object alertValue;
	private Integer createTime;

	public AlertEntry() {
	}

	public AlertEntry(Integer userId, Long nodeId) {
		this.userId = userId;
		this.nodeId = nodeId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public Integer getAlertType() {
		return alertType;
	}

	public void setAlertType(Integer alertType) {
		this.alertType = alertType;
	}

	public Object getAlertValue() {
		return alertValue;
	}

	public void setAlertValue(Object alertValue) {
		this.alertValue = alertValue;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "AlertEntry{" +
				"userId=" + userId +
				", nodeId=" + nodeId +
				", alertType=" + alertType +
				", alertValue=" + alertValue +
				", createTime=" + createTime +
				'}';
	}
}
