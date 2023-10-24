package com.tencoding.ADayOfLearning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component // IoC 대상 - 2개 이상의 빈을 등록해야 할 때 사용 
public class WebMvcConfig implements WebMvcConfigurer {
	
	// DI 처리 
	@Autowired
	private AuthInterceptor authInterceptor;
	
	@Autowired
	private AdminInterceptor amdinInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor)
		.addPathPatterns("/reserve/**");
		
		registry.addInterceptor(amdinInterceptor).addPathPatterns("/admin/**");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}