package com.helmsman.sarah.node.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ppblock.common.utils.BeanUtils;
import com.ppblock.common.utils.HttpUtil;
import com.ppblock.common.utils.StringUtil;
import com.helmsman.sarah.node.conf.SarahConfig;
import com.helmsman.sarah.node.model.AlertEntry;
import com.helmsman.sarah.node.model.AlertSetting;
import com.helmsman.sarah.common.vo.SarahReqVo;
import com.helmsman.sarah.node.service.SarahApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yangjian
 * @since 2019-01-17 下午3:29.
 */
@Service("sarahApiService")
public class SarahApiServiceImpl implements SarahApiService {

	private static Logger logger = LoggerFactory.getLogger(SarahApiService.class);

	@Autowired
	private SarahConfig sarahConfig;

	@Override
	public AlertSetting getAlertSetting(Integer userId) {

		if (userId <= 0) {
			return null;
		}
		HashMap<String, Object> params = new HashMap<>(1);
		params.put("userId", userId);
		try {
			String result = HttpUtil.get(sarahConfig.getAlertSettingUrl(), params);
			SarahReqVo sarahReqVo = StringUtil.jsonDecode(result, SarahReqVo.class);
			// convert LinkedHashMap to AlertSetting bean
			LinkedHashMap<String, Object> data = (LinkedHashMap) sarahReqVo.getData();
			JSONObject jsonObject = new JSONObject(data);
			AlertSetting alertSetting = new AlertSetting(userId);
			alertSetting.setOpenAlert(jsonObject.getBoolean("openAlert"));
			alertSetting.setAlertMask(jsonObject.getIntValue("alertMask"));
			alertSetting.setAlertInterval(jsonObject.getIntValue("alertInterval"));
			alertSetting.setLoadAvg(jsonObject.getFloat("loadAvg"));
			alertSetting.setRamPercent(jsonObject.getFloat("ramPercent"));
			alertSetting.setDiskPercent(jsonObject.getFloat("diskPercent"));
			alertSetting.setIncomingBd(jsonObject.getInteger("incomingBd"));
			alertSetting.setOutgoingBd(jsonObject.getInteger("outgoingBd"));
			alertSetting.setUpdateTime(jsonObject.getInteger("updateTime"));
			return alertSetting;
		} catch (IOException e) {
			logger.info("Fetch alert setting fail.");
			return null;
		}
	}

	@Override
	public void postAlertMessage(AlertEntry alertEntry) {
		if (null == alertEntry || null == alertEntry.getUserId()
				|| null == alertEntry.getNodeId()) {
			return;
		}
		Map<String, Object> params = BeanUtils.BeanToMap(alertEntry);
		try {
			String ret = HttpUtil.post(sarahConfig.getAlertPostUrl(), params);
			SarahReqVo sarahReqVo = StringUtil.jsonDecode(ret, SarahReqVo.class);
			if (null != sarahReqVo && sarahReqVo.isSuccess()) {
				logger.info("Post alert message successfully. {}", alertEntry);
			} else {
				logger.info(ret);
			}
		} catch (IOException e) {
			// TODO: set logs here
		}
	}
}
