package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

/**
 * Bean定義クラス
 * 
 * @author ys-fj
 *
 */
@Configuration
public class BeanDefine {

	/**
	 * パスワードエンコーダーのBean定義を行います。
	 * 
	 * @return パスワードエンコーダー(BCrypt形式)
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * マッピングフレームワークのBean定義を行います。
	 * 
	 * @return マッピングフレームワーク(Dozer)
	 */
	@Bean
	Mapper mapper() {
		return DozerBeanMapperBuilder.buildDefault();
	}

}
