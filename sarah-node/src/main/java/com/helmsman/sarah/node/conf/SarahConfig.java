package com.helmsman.sarah.node.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yangjian
 * @since 2019-01-16 下午6:06.
 */
@ConfigurationProperties(prefix = "sarah")
@Component
public class SarahConfig {

	private String alertPostUrl;
	private String alertSettingUrl;

	public String getAlertPostUrl() {
		return alertPostUrl;
	}

	public void setAlertPostUrl(String alertPostUrl) {
		this.alertPostUrl = alertPostUrl;
	}

	public String getAlertSettingUrl() {
		return alertSettingUrl;
	}

	public void setAlertSettingUrl(String alertSettingUrl) {
		this.alertSettingUrl = alertSettingUrl;
	}
}
