package com.helmsman.sarah.node;

import com.helmsman.sarah.node.model.AlertSetting;
import com.helmsman.sarah.node.service.SarahApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SarahNodeApplicationTests {

	@Autowired
	private SarahApiService apiService;

	@Test
	public void getAlertSetting() {

		Integer userId = 1;
		AlertSetting alertSetting = apiService.getAlertSetting(userId);
		System.out.println(alertSetting);
	}

}

