package com.example.demo.authentication;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー情報の定義、生成クラス
 * 
 * @author ys-fj
 *
 */
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	/** ユーザー情報テーブルRepository */
	private final UserInfoRepository repository;

	/** アカウントロックを行うログイン失敗回数境界値 */
	@Value("${security.locking-border-count}")
	private int lockingBorderCount;

	/** アカウントロックの継続時間 */
	@Value("${security.locking-time}")
	private int lockingTime;

	/**
	 * 引数のログインIDを使ってDBへユーザー検索を行い、該当データから後の認証処理で使用するユーザー情報を生成します。
	 * 
	 * <p>なお、後の認証処理で使用するユーザー情報は以下のように設定します。
	 * <table border="1">
	 * <caption>ユーザー情報の各項目</caption>
	 * <tr><td>ログインID</td><td>DBに登録されているユーザー情報のログインID</td></tr>
	 * <tr><td>パスワード</td><td>DBに登録されているユーザー情報のパスワード<br>※後の認証処理専用で利用後はクリアされセッションには保管されません。</td></tr>
	 * <tr><td>権限</td><td>DBに登録されているユーザー情報の権限</td></tr>
	 * <tr><td>利用可否</td><td>DBに登録されているユーザー情報の利用可否</td></tr>
	 * <tr><td>アカウントロック</td><td>DBに登録されているユーザー情報のアカウントロック日時と<br>既定のアカウントロック時間(プロパティファイルに設定)からロック解除時間を算出し<br>その時間と現在日時と比較して判定します。</td></tr>
	 * </table>
	 * 
	 * @param username ログインID
	 * @throws UsernameNotFoundException DB検索でユーザーが見つからなかった場合
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var userInfo = repository.findById(username)
				.orElseThrow(() -> new UsernameNotFoundException(username));

		var accountLockedTime = userInfo.getAccountLockedTime();
		var isAccountLocked = accountLockedTime != null
				&& accountLockedTime.plusHours(lockingTime).isAfter(LocalDateTime.now());

		return User.withUsername(userInfo.getLoginId())
				.password(userInfo.getPassword())
				.authorities(userInfo.getAuthorityKind().getCode())
				.disabled(userInfo.getUserStatusKind().isDisabled())
				.accountLocked(isAccountLocked)
				.build();
	}

	/**
	 * 認証失敗時にログイン失敗回数を加算や、ロック日時の更新を行います。
	 * 
	 * <p>ただしロック日時の更新は、ログイン失敗回数が既定の回数(プロパティファイルに設定)に達した際に行われます。
	 * 
	 * @param event 認証失敗時のイベント情報
	 */
	@EventListener
	public void handle(AuthenticationFailureBadCredentialsEvent event) {
		var loginId = event.getAuthentication().getName();
		repository.findById(loginId).ifPresent(userInfo -> {
			repository.save(userInfo.incrementLoginFailureCount());

			var isReachFailureCount = userInfo.getLoginFailureCount() == lockingBorderCount;
			if (isReachFailureCount) {
				repository.save(userInfo.updateAccountLocked());
			}
		});
	}

	/**
	 * 認証成功時にログイン失敗回数をリセットします。
	 * 
	 * @param event 認証成功時のイベント情報
	 */
	@EventListener
	public void handle(AuthenticationSuccessEvent event) {
		var loginId = event.getAuthentication().getName();
		repository.findById(loginId).ifPresent(userInfo -> {
			repository.save(userInfo.resetLoginFailureInfo());
		});
	}

}
