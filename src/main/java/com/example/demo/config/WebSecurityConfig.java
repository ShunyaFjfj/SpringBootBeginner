package com.example.demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.constant.UrlConst;

import lombok.RequiredArgsConstructor;

/**
 * Spring Securityカスタマイズクラス
 * 
 * @author ys-fj
 *
 */
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

	/** パスワードエンコーダー */
	private final PasswordEncoder passwordEncoder;

	/** ユーザー情報取得Service */
	private final UserDetailsService userDetailsService;

	/** メッセージ取得 */
	private final MessageSource messageSource;

	/** ユーザー名のname属性 */
	private final String USERNAME_PARAMETER = "loginId";

	/**
	 * Spring Securityの各種カスタマイズを行います。
	 * 
	 * <p>カスタマイズ設定するのは、以下の項目になります。
	 * <ul>
	 * <li>認証不要URL</li>
	 * <li>ログイン画面のURL</li>
	 * <li>usernameとして利用するリクエストパラメーター名</li>
	 * <li>ログイン成功時のリダイレクト先URL</li>
	 * </ul>
	 * 
	 * @param http セキュリティ設定
	 * @return カスタマイズ結果
	 * @throws Exception 予期せぬ例外が発生した場合
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
				.authorizeHttpRequests(
						authorize -> authorize.requestMatchers(UrlConst.NO_AUTHENTICATION).permitAll()
								.anyRequest().authenticated())
				.formLogin(
						login -> login.loginPage(UrlConst.LOGIN) // 自作ログイン画面(Controller)を使うための設定
								.usernameParameter(USERNAME_PARAMETER) // ユーザ名パラメータのname属性
								.defaultSuccessUrl(UrlConst.MENU)); // ログイン成功後のリダイレクトURL

		return http.build();
	}

	/**
	 * Providerのカスタマイズを行い、独自Providerを返却します。
	 * 
	 * <p>カスタマイズ設定するのは、以下のフィールドになります。
	 * <ul>
	 * <li>UserDetailsService</li>
	 * <li>PasswordEncoder</li>
	 * <li>MessageSource</li>
	 * </ul>
	 * 
	 * @return カスタマイズしたProvider情報
	 */
	@Bean
	AuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		provider.setMessageSource(messageSource);

		return provider;
	}
}
