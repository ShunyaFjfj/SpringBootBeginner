package com.example.demo.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.constant.SignupConfirmStatus;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー登録情報確認画面Service実装クラス
 * 
 * @author ys-fj
 *
 */
@Service
@RequiredArgsConstructor
public class SignupConfirmServiceImpl implements SignupConfirmService {

	/** ユーザー情報テーブルRepositoryクラス */
	private final UserInfoRepository repository;

	/** パスワードエンコーダー */
	private final PasswordEncoder passwordEncoder;

	/** ワンタイムコード有効時間 */
	@Value("${one-time-code.valid-time}")
	private Duration oneTimeCodeValidTime = Duration.ZERO;

	@Override
	public SignupConfirmStatus updateUserAsSignupCompletion(String loginId, String oneTimeCode) {
		var updateInfoOpt = repository.findById(loginId);
		if (updateInfoOpt.isEmpty()) {
			return SignupConfirmStatus.FAILURE_BY_NOT_EXISTS_TENTATIVE_USER;
		}
		var updateInfo = updateInfoOpt.get();

		if (!passwordEncoder.matches(oneTimeCode, updateInfo.getOneTimeCode())) {
			return SignupConfirmStatus.FAILURE_BY_WRONG_ONE_TIME_CODE;
		}

		var oneTimeCodeTimeLimit = updateInfo.getOneTimeCodeSendTime().plus(oneTimeCodeValidTime);
		var isOneTimeCodeAvailable = oneTimeCodeTimeLimit.isAfter(LocalDateTime.now());
		if (!isOneTimeCodeAvailable) {
			return SignupConfirmStatus.FAILURE_BY_EXPIRED_ONE_TIME_CODE;
		}

		updateInfo.setSignupCompleted(true);
		updateInfo.setOneTimeCode(null);
		updateInfo.setOneTimeCodeSendTime(null);
		updateInfo.setUpdateTime(LocalDateTime.now());
		updateInfo.setUpdateUser(loginId);
		try {
			repository.save(updateInfo);
		} catch (Exception e) {
			return SignupConfirmStatus.FAILURE_BY_DB_ERROR;
		}

		return SignupConfirmStatus.SUCCEED;
	}

}
