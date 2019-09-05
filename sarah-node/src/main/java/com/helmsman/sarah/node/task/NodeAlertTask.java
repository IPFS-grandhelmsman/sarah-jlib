package com.helmsman.sarah.node.task;

import com.ppblock.common.utils.DateUtil;
import com.helmsman.sarah.node.model.AlertEntry;
import com.helmsman.sarah.node.model.AlertSetting;
import com.helmsman.sarah.node.model.NodeEntry;
import com.helmsman.sarah.node.service.AlertSettingService;
import com.helmsman.sarah.node.service.NodeService;
import com.helmsman.sarah.node.service.SarahApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Alarm task.
 * Traverse the Map to find the alarm node.
 * @author yangjian
 * @since 2019-01-16 下午6:45.
 */
@Component
public class NodeAlertTask {

	private static Logger logger = LoggerFactory.getLogger(NodeAlertTask.class);
	// heartbeat of node server, if more than 40s after a server send a heartbeat should be marked as a fault node.
	private static final Integer NODE_HEART_BEAT = 40;

	@Autowired
	private NodeService nodeService;
	@Autowired
	private SarahApiService sarahApiService;
	// Create a thread pool with a capacity of 100
	private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(100);
	@Autowired
	private AlertSettingService alertSettingService;

	/**
	 * Analyze alert information
	 */
	@Scheduled(fixedDelay = 5000)
	public void analyzeAlertInfo() {
		Map<Long, NodeEntry> nodeMap = nodeService.getNodeMap();
		Map<Integer, AlertSetting> alertSettings = alertSettingService.getAlertSettings();
		Iterator iterator = nodeMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			NodeEntry next = (NodeEntry) entry.getValue();
			// push alert setting to map if not exist
			if (null == alertSettings.get(next.getUserId())) {
				AlertSetting alertSetting = sarahApiService.getAlertSetting(next.getUserId());
				alertSettingService.updateUserAlertSetting(alertSetting);
			}
			fixedThreadPool.execute(new AlertTask(next, alertSettings.get(next.getUserId())));
		}
	}

	/**
	 * Alert task runnable
	 */
	class AlertTask implements Runnable {

		private NodeEntry nodeEntry;
		private AlertSetting alertSetting;

		public AlertTask(NodeEntry nodeEntry, AlertSetting alertSetting) {
			this.nodeEntry = nodeEntry;
			this.alertSetting = alertSetting;
		}

		@Override
		public void run() {

			if (!alertSetting.isOpenAlert()) {
				logger.info("This user is not open alert function, skip. {}", alertSetting);
				return;
			}

			AlertEntry alertEntry = new AlertEntry(nodeEntry.getUserId(), nodeEntry.getNodeId());
			alertEntry.setCreateTime(DateUtil.dateToInteger(new Date()));
			// Check if it a fault node
			Integer now = DateUtil.dateToInteger(new Date());
			Integer alertInterval = alertSetting.getAlertInterval() * 60;
			// if this node had send a alert message, wait for 3600 secs to send another
			if (now - nodeEntry.getSendAlertTime() <= alertInterval) {
				logger.info("This node had send an alert message in {} secs, skip it.", alertInterval);
				return;
			}
			if ( now - nodeEntry.getUpdateTime() > NODE_HEART_BEAT) {
				alertEntry.setAlertType(AlertEntry.ALERT_TYPE_FAULT);
				alertEntry.setAlertValue("fault|unknown");
				sarahApiService.postAlertMessage(alertEntry);
				nodeEntry.setSendAlertTime(DateUtil.dateToInteger(new Date()));
				logger.warn("There is a fault node, {}", nodeEntry);
				return;
			}
			// load average alarm
			if (alertSetting.getLoadAvg() > 0 && nodeEntry.getLoadAvg() > alertSetting.getLoadAvg()) {
				alertEntry.setAlertType(AlertEntry.ALERT_TYPE_LOADAVG);
				alertEntry.setAlertValue("loadavg|"+nodeEntry.getLoadAvg());
				sarahApiService.postAlertMessage(alertEntry);
				nodeEntry.setSendAlertTime(DateUtil.dateToInteger(new Date()));
				logger.info("LoadAvg Alert, limit: {}, value: {}", alertSetting.getLoadAvg(), nodeEntry.getLoadAvg());
				// TODO send alert info to SarahOS api
			}

			// memory alarm
			if (alertSetting.getRamPercent() > 0 && nodeEntry.getRamPercent() > alertSetting.getRamPercent()) {
				alertEntry.setAlertType(AlertEntry.ALERT_TYPE_RAM);
				alertEntry.setAlertValue("ram|"+nodeEntry.getRamPercent());
				sarahApiService.postAlertMessage(alertEntry);
				nodeEntry.setSendAlertTime(DateUtil.dateToInteger(new Date()));
				logger.info("RAM Alert, limit: {}, value: {}", alertSetting.getRamPercent(), nodeEntry.getRamPercent());
				// TODO send alert info to SarahOS api
			}

			// disk alarm
			if (alertSetting.getDiskPercent() > 0 && nodeEntry.getDiskPercent() > alertSetting.getDiskPercent()) {
				alertEntry.setAlertType(AlertEntry.ALERT_TYPE_DISK);
				alertEntry.setAlertValue("disk|"+nodeEntry.getDiskPercent());
				sarahApiService.postAlertMessage(alertEntry);
				nodeEntry.setSendAlertTime(DateUtil.dateToInteger(new Date()));
				logger.info("Disk Alert, limit: {}, value: {}", alertSetting.getDiskPercent(), nodeEntry.getDiskPercent());
				// TODO send alert info to SarahOS api
			}

			// upload bandwidth alarm
			if (alertSetting.getIncomingBd() > 0 && nodeEntry.getIncomingBd() > alertSetting.getIncomingBd()) {
				alertEntry.setAlertType(AlertEntry.ALERT_TYPE_INCOMING_BD);
				alertEntry.setAlertValue("incoming_bd|"+nodeEntry.getIncomingBd());
				sarahApiService.postAlertMessage(alertEntry);
				nodeEntry.setSendAlertTime(DateUtil.dateToInteger(new Date()));
				logger.info("Income bandwidth Alert, limit: {}, value: {}",
						alertSetting.getIncomingBd(), nodeEntry.getIncomingBd());
				// TODO send alert info to SarahOS api
			}

			// download bandwidth alarm
			if (alertSetting.getOutgoingBd() > 0 && nodeEntry.getOutgoingBd() > alertSetting.getOutgoingBd()) {
				alertEntry.setAlertType(AlertEntry.ALERT_TYPE_OUTGOING_BD);
				alertEntry.setAlertValue("outgoing_bd|"+nodeEntry.getOutgoingBd());
				sarahApiService.postAlertMessage(alertEntry);
				nodeEntry.setSendAlertTime(DateUtil.dateToInteger(new Date()));
				logger.info("Outgoing bandwidth Alert, limit: {}, value: {}",
						alertSetting.getOutgoingBd(), nodeEntry.getOutgoingBd());
				// TODO send alert info to SarahOS api
			}
		}
	}
}
