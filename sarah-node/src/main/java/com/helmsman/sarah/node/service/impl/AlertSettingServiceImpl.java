package com.helmsman.sarah.node.service.impl;

import com.google.common.base.Preconditions;
import com.helmsman.sarah.node.model.AlertSetting;
import com.helmsman.sarah.node.service.AlertSettingService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangjian
 * @since 2019-02-13 上午11:00.
 */
@Service("alertSettingService")
public class AlertSettingServiceImpl implements AlertSettingService {

	// Settings of all users
	private static Map<Integer, AlertSetting> userAlertSettings = new ConcurrentHashMap<>(256);

	@Override
	public Map<Integer, AlertSetting> getAlertSettings()
	{
		return userAlertSettings;
	}

	@Override
	public AlertSetting getUserAlertSetting(Integer userId)
	{
		return userAlertSettings.get(userId);
	}

	@Override
	public void updateUserAlertSetting(AlertSetting setting)
	{
		Preconditions.checkArgument(null != setting, "alert setting should not be null.");
		Preconditions.checkArgument(null != setting.getUserId(), "alert userId is needed.");
		userAlertSettings.put(setting.getUserId(), setting);
	}
}
