package com.helmsman.sarah.node.service;

import com.helmsman.sarah.node.model.NodeEntry;

import java.util.Map;

/**
 * @author yangjian
 * @since 2019-01-16 下午5:37.
 */
public interface NodeService {

	/**
	 * get the map that stored the information of the client node
	 * @return
	 */
	Map<Long, NodeEntry> getNodeMap();

	/**
	 * put an item of node information to the map
	 * @param nodeEntry
	 */
	void putDataToNodeMap(NodeEntry nodeEntry);

	/**
	 * get node information by node id
	 * @param nodeId
	 * @return
	 */
	NodeEntry getNodeEntryById(Long nodeId);
}
