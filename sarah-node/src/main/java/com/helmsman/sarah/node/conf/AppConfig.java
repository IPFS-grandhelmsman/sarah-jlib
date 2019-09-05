package com.helmsman.sarah.node.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * App bean configure
 * @author yangjian
 * @since 2019-01-16 下午3:25.
 */
@Configuration
public class AppConfig extends WebMvcConfigurationSupport {

	/**
	 * MVC resource configure
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		// set handler for API document request
		registry.addResourceHandler("docs/**").addResourceLocations("classpath:/docs/_book/");
	}
}
