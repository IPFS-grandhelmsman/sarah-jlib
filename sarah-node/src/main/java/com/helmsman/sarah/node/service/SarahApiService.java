package com.helmsman.sarah.node.service;

import com.helmsman.sarah.node.model.AlertEntry;
import com.helmsman.sarah.node.model.AlertSetting;

/**
 * Api service for sarahOS
 * @author yangjian
 * @since 2019-01-17 下午3:35.
 */
public interface SarahApiService {

	/**
	 * get alert setting of the specified user
	 * @param userId
	 * @return
	 */
	AlertSetting getAlertSetting(Integer userId);

	/**
	 * post alert message to sarahOS
	 * @param alertEntry
	 */
	void postAlertMessage(AlertEntry alertEntry);

}
