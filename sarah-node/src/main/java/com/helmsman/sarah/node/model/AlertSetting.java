package com.helmsman.sarah.node.model;

import com.ppblock.common.utils.StringUtil;

/**
 * AlertEntry setting for user
 * @author yangjian
 * @since 2019-01-17 上午9:49.
 */
public class AlertSetting {

	public static final int ALERT_METHOD_MOBILE = 1 << 1; // 短信报警
	public static final int ALERT_METHOD_EMAIL = 1 << 2; // 邮件报警
	public static final int ALERT_METHOD_WECHAT = 1 << 3; // 微信报警

	private Integer userId;
	// whether open alarm function
	private boolean openAlert;
	// alert
	private Integer alertMask;
	// Alarm interval, default value is 60 minutes
	private Integer alertInterval = 60;

	private Float loadAvg;
	private Float ramPercent;
	private Float diskPercent;
	// upload bandwidth
	private Integer incomingBd;
	// download bandwidth
	private Integer outgoingBd;
	private Integer updateTime;

	public AlertSetting() {
	}

	public AlertSetting(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public boolean isOpenAlert() {
		return openAlert;
	}

	public void setOpenAlert(boolean openAlert) {
		this.openAlert = openAlert;
	}

	public int getAlertMask() {
		return alertMask;
	}

	public void setAlertMask(Integer alertMask) {
		this.alertMask = alertMask;
	}

	public Float getLoadAvg() {
		return loadAvg;
	}

	public void setLoadAvg(Float loadAvg) {
		this.loadAvg = loadAvg;
	}

	public Float getRamPercent() {
		return ramPercent;
	}

	public void setRamPercent(Float ramPercent) {
		this.ramPercent = ramPercent;
	}

	public Float getDiskPercent() {
		return diskPercent;
	}

	public void setDiskPercent(Float diskPercent) {
		this.diskPercent = diskPercent;
	}

	public Integer getIncomingBd() {
		return incomingBd;
	}

	public void setIncomingBd(Integer incomingBd) {
		this.incomingBd = incomingBd;
	}

	public Integer getOutgoingBd() {
		return outgoingBd;
	}

	public void setOutgoingBd(Integer outgoingBd) {
		this.outgoingBd = outgoingBd;
	}

	public Integer getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Integer updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getAlertInterval() {
		return alertInterval;
	}

	public void setAlertInterval(Integer alertInterval) {
		if (alertInterval > 0) {
			this.alertInterval = alertInterval;
		}
	}

	@Override
	public int hashCode() {
		return StringUtil.powerHash(this.userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AlertSetting) {
			return ((AlertSetting) obj).getUserId() == this.getUserId();
		}
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "AlertSetting{" +
				"userId=" + userId +
				", openAlert=" + openAlert +
				", alertMask=" + alertMask +
				", alertInterval=" + alertInterval +
				", loadAvg=" + loadAvg +
				", ramPercent=" + ramPercent +
				", diskPercent=" + diskPercent +
				", incomingBd=" + incomingBd +
				", outgoingBd=" + outgoingBd +
				", updateTime=" + updateTime +
				'}';
	}
}
