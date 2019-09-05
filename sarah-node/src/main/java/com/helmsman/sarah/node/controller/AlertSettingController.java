package com.helmsman.sarah.node.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import com.helmsman.sarah.node.model.AlertSetting;
import com.helmsman.sarah.node.service.AlertSettingService;
import com.helmsman.sarah.common.vo.SarahResVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjian
 * @since 2019-02-13 上午11:34.
 */
@RestController
@RequestMapping("/alert/setting")
public class AlertSettingController {

	static final Logger logger = LoggerFactory.getLogger(AlertSettingController.class);

	@Autowired
	private AlertSettingService alertSettingService;

	/**
	 * update user's alert setting
	 *
	 * @param jsonParams
	 * @return
	 */
	@PostMapping("/update")
	public SarahResVo update(@RequestBody JSONObject jsonParams)
	{
		Integer userId = jsonParams.getInteger("userId");
		Preconditions.checkNotNull(userId, "userId is needed.");
		AlertSetting setting = new AlertSetting(userId);
		setting.setLoadAvg(jsonParams.getFloat("loadAvg"));
		setting.setOpenAlert(jsonParams.getBoolean("openAlert"));
		setting.setAlertMask(jsonParams.getIntValue("alertMask"));
		setting.setAlertInterval(jsonParams.getIntValue("alertInterval"));
		setting.setRamPercent(jsonParams.getFloat("ramPercent"));
		setting.setDiskPercent(jsonParams.getFloat("diskPercent"));
		setting.setOutgoingBd(jsonParams.getInteger("outgoingBd"));
		setting.setIncomingBd(jsonParams.getInteger("incomingBd"));
		setting.setUpdateTime(jsonParams.getInteger("updateTime"));

		alertSettingService.updateUserAlertSetting(setting);
		logger.info("update alert setting: {}", setting);
		return SarahResVo.success();
	}
}
