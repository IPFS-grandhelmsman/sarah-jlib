package com.helmsman.sarah.node.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import com.helmsman.sarah.common.enums.ErrorCode;
import com.helmsman.sarah.node.model.NodeEntry;
import com.helmsman.sarah.node.service.NodeService;
import com.helmsman.sarah.common.vo.SarahResVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * node post api
 * @author yangjian
 * @since 2019-01-16 下午3:10.
 */
@RestController
@RequestMapping("/node")
public class NodeController {

	static Logger logger = LoggerFactory.getLogger(NodeController.class);
	@Autowired
	private NodeService nodeService;

	/**
	 * Receiving data package from SarahOS and add it to the node post map
	 * 接收 SarahOS 推送过来的节点信息包
	 * @param jsonParams
	 * @return
	 */
	@PostMapping("/data/post")
	public SarahResVo dataPost(@RequestBody JSONObject jsonParams) {

		Long nodeId = jsonParams.getLong("nodeId");
		Integer userId = jsonParams.getInteger("userId");
		Float loadAvg = jsonParams.getFloat("loadAvg");
		Float ramPercent = jsonParams.getFloat("ramPercent");
		Float diskPercent = jsonParams.getFloat("diskPercent");
		Integer ramFree = jsonParams.getInteger("ramFree");
		Long diskFree = jsonParams.getLong("diskFree");
		Integer incomingBd = jsonParams.getInteger("incomingBd");
		Integer outgoingBd = jsonParams.getInteger("outgoingBd");
		Integer updateTime = jsonParams.getInteger("updateTime");
		Integer status = jsonParams.getInteger("status");

		Preconditions.checkArgument(null != nodeId, "Node id is needed.");
		Preconditions.checkArgument(null != userId, "User id is needed.");

		NodeEntry nodeEntry;
		boolean isUpdate = true;
		nodeEntry = nodeService.getNodeEntryById(nodeId);
		if (null == nodeEntry) { // add a new NodeEntry
			nodeEntry = new NodeEntry(nodeId, userId);
			isUpdate = false;
		}
		if (null != loadAvg) {
			nodeEntry.setLoadAvg(loadAvg);
		}
		if (null != ramPercent) {
			nodeEntry.setRamPercent(ramPercent);
		}
		if (null != diskPercent) {
			nodeEntry.setDiskPercent(diskPercent);
		}
		if (null != ramFree) {
			nodeEntry.setRamFree(ramFree);
		}
		if (null != diskFree) {
			nodeEntry.setDiskFree(diskFree);
		}
		if (null != incomingBd) {
			nodeEntry.setIncomingBd(incomingBd);
		}
		if (null != outgoingBd) {
			nodeEntry.setOutgoingBd(outgoingBd);
		}
		if (null != status) {
			nodeEntry.setStatus(status);
		}
		if (null != updateTime) {
			nodeEntry.setUpdateTime(updateTime);
		}

		if (!isUpdate) {
			nodeService.putDataToNodeMap(nodeEntry);
		}
		logger.info("Receive a new NodeEntry: {}", nodeEntry);
		return SarahResVo.success();
	}

	/**
	 * Get the packet of the specified node
	 * 获取指定节点的数据包
	 * @param request
	 * @return
	 */
	@GetMapping("/data/get")
	public SarahResVo dataGet(HttpServletRequest request) {
		Long nodeId = Long.valueOf(request.getParameter("nodeId"));
		Preconditions.checkArgument(null != nodeId, "Node id is needed.");
		NodeEntry nodeEntry = nodeService.getNodeEntryById(nodeId);
		if (null == nodeEntry) {
			return SarahResVo.instance(ErrorCode.NO_RECORDS);
		} else {
			return SarahResVo.success(nodeEntry);
		}
	}
}
