package com.example.demo.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanDefine {

	// TODO 後からコメントを外す
	//	@Bean
	//	PasswordEncoder passwordEncoder() {
	//		return new BCryptPasswordEncoder();
	//	}

	@Bean
	Mapper mapper() {
		return new DozerBeanMapper();
	}

}
