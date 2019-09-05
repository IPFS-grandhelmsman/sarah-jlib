package com.helmsman.sarah.node.service.impl;

import com.helmsman.sarah.node.model.NodeEntry;
import com.helmsman.sarah.node.service.NodeService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangjian
 * @since 2019-01-16 下午5:59.
 */
@Service("nodeService")
public class NodeServiceImpl implements NodeService {

	private static ConcurrentHashMap<Long, NodeEntry> nodeMap = new ConcurrentHashMap();

	@Override
	public Map<Long, NodeEntry> getNodeMap() {
		return nodeMap;
	}

	@Override
	public void putDataToNodeMap(NodeEntry nodeEntry) {
		if (null == nodeEntry) {
			return;
		}
		nodeMap.put(nodeEntry.getNodeId(), nodeEntry);
	}

	@Override
	public NodeEntry getNodeEntryById(Long nodeId) {
		if (null == nodeId) {
			return null;
		}
		return nodeMap.get(nodeId);
	}
}
