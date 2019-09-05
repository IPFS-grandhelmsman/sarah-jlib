package com.helmsman.sarah.node.controller;

import com.helmsman.sarah.node.service.SarahApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yangjian
 * @since 2019-01-16 下午3:39.
 */
@RestController
public class IndexController {

	@Autowired
	private SarahApiService sarahApiService;

	/**
	 * Set  home page
	 * @return
	 */
	@GetMapping({"", "/"})
	public String index() {
		return "<h1>Welcome to sarah-jnode, the service is running.</h1>";
	}

	/**
	 * If user send a request to /docs, then jump to the document page automatically.
	 * @param response
	 * @throws IOException
	 */
	@GetMapping("/docs")
	public void docs(HttpServletResponse response) throws IOException {
		response.sendRedirect("/docs/index.html");
	}

	@GetMapping("/test")
	public Object test() {
		return sarahApiService.getAlertSetting(1);
	}
}
