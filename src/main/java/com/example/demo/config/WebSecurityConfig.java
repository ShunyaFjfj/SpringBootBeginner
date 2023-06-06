package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Securityカスタマイズ用
 * 
 * @author ys-fj
 *
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

	/**
	 * Spring Securityカスタマイズ用
	 * 
	 * @param http カスタマイズパラメータ
	 * @return カスタマイズ結果
	 * @throws Exception 予期せぬ例外
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.formLogin(
				login -> login.loginPage("/login") // 自作ログイン画面(Controller)を使うための設定
						.usernameParameter("loginId") // ユーザ名パラメータのname属性
						.defaultSuccessUrl("/menu")); // ログイン成功後のリダイレクトURL

		return http.build();
	}
}
