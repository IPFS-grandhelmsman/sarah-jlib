package com.helmsman.sarah.node.model;

import com.ppblock.common.utils.StringUtil;

/**
 * Information for a client node
 * @author yangjian
 * @since 2019-01-16 下午3:58.
 */
public class NodeEntry {

	public static final int STATUS_FAULT = 0; // 故障
	public static final int STATUS_NORMAL = 1; // 运行中
	public static final int STATUS_RESTARTING = 2; //重启中
	// node id
	private Long nodeId;
	// user id
	private Integer userId;
	// load average
	private Float loadAvg;
	// ram used percent
	private Float ramPercent;
	// available memory
	private Integer ramFree;
	// disk used percent
	private Float diskPercent;
	// available disk space
	private Long diskFree;
	// upload bandwidth
	private Integer incomingBd;
	// download bandwidth
	private Integer outgoingBd;
	// calculate ability of cpu
	private Float cpuPower;
	// storage ability of disk
	private Float storagePower;
	// last update time
	private Integer updateTime;
	// node state
	private int status = STATUS_NORMAL;
	// last send alert time
	private Integer sendAlertTime = 0;

	public NodeEntry() {
	}

	public NodeEntry(Long nodeId, Integer userId) {
		this.nodeId = nodeId;
		this.userId = userId;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public Integer getRamFree() {
		return ramFree;
	}

	public void setRamFree(Integer ramFree) {
		this.ramFree = ramFree;
	}

	public Float getDiskPercent() {
		return diskPercent;
	}

	public void setDiskPercent(Float diskPercent) {
		this.diskPercent = diskPercent;
	}

	public Long getDiskFree() {
		return diskFree;
	}

	public void setDiskFree(Long diskFree) {
		this.diskFree = diskFree;
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

	public Float getCpuPower() {
		return cpuPower;
	}

	public void setCpuPower(Float cpuPower) {
		this.cpuPower = cpuPower;
	}

	public Float getStoragePower() {
		return storagePower;
	}

	public void setStoragePower(Float storagePower) {
		this.storagePower = storagePower;
	}

	public Integer getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Integer updateTime) {
		this.updateTime = updateTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getSendAlertTime() {
		return sendAlertTime;
	}

	public void setSendAlertTime(Integer sendAlertTime) {
		this.sendAlertTime = sendAlertTime;
	}

	@Override
	public int hashCode() {
		return StringUtil.powerHash(this.nodeId);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof NodeEntry) {
			NodeEntry obj = (NodeEntry) o;
			return obj.getNodeId() == this.getNodeId() && obj.getUpdateTime() == this.getUpdateTime();
		}
		return super.equals(o);
	}

	@Override
	public String toString() {
		return "NodeEntry{" +
				"nodeId=" + nodeId +
				", userId=" + userId +
				", loadAvg=" + loadAvg +
				", ramPercent=" + ramPercent +
				", ramFree=" + ramFree +
				", diskPercent=" + diskPercent +
				", diskFree=" + diskFree +
				", incomingBd=" + incomingBd +
				", outgoingBd=" + outgoingBd +
				", cpuPower=" + cpuPower +
				", storagePower=" + storagePower +
				", updateTime=" + updateTime +
				", status=" + status +
				", sendAlertTime=" + sendAlertTime +
				'}';
	}
}
