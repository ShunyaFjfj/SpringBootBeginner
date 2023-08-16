package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ユーザー登録結果メッセージEnumクラス
 * 
 * @author ys-fj
 *
 */
@Getter
@AllArgsConstructor
public enum SignupMessage {

	/** 登録成功 */
	SUCCEED(MessageConst.SIGNUP_RESIST_SUCCEED, false),

	/** 既に登録されているログインID */
	EXISTED_LOGIN_ID(MessageConst.SIGNUP_EXISTED_LOGIN_ID, true);

	/** メッセージID */
	private String messageId;

	/** エラー有無 */
	private boolean isError;
}
