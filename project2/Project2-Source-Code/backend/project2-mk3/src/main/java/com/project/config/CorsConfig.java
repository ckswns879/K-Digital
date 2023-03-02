package com.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:3000", "http://10.125.121.177:3000", "http://10.125.121.204:3000")
			.allowedMethods("*")	//preflight때문에 option method가 포함되어야 됨. "*"로 설정으로 모든 method 포함
	        .allowedHeaders("*")
	        .exposedHeaders("*")
	        .allowCredentials(true)
	        .maxAge(3600);
	}
	
}