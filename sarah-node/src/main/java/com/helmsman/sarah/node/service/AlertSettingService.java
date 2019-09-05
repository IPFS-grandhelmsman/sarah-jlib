package com.helmsman.sarah.node.service;

import com.helmsman.sarah.node.model.AlertSetting;

import java.util.Map;

/**
 * alert setting service for user
 * @author yangjian
 * @since 2019-02-13 上午10:42.
 */
public interface AlertSettingService {

	/**
	 * get alert settings for all user
	 * @return
	 */
	Map<Integer, AlertSetting> getAlertSettings();

	/**
	 * get alert setting by user id
	 * @param userId
	 * @return
	 */
	AlertSetting getUserAlertSetting(Integer userId);

	/**
	 * update specified user's alert settings, if the record is not exists, add a new item.
	 * @param setting
	 */
	void updateUserAlertSetting(AlertSetting setting);

}
