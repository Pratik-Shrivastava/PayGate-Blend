package com.pratik.payGateBlend.config;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@ComponentScan(basePackages = {"com.pratik.payGateBlend"})
@AllArgsConstructor(onConstructor_ = @__({@Autowired}))
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(@NotNull CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "TRACE");
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
