package com.example.demo.service;

import com.example.demo.constant.SignupConfirmStatus;

/**
 * ユーザー登録情報確認画面Serviceインターフェース
 * 
 * @author ys-fj
 *
 */
public interface SignupConfirmService {

	/**
	 * ログインIDとワンタイムコードを使用して本登録可能な仮登録ユーザーが存在するか検索します。
	 * 
	 * @param loginId ログインID
	 * @param oneTimeCode ワンタイムコード
	 * @return 検索結果(エラーが無ければ{@code SignupResult.SUCCEED})
	 */
	SignupConfirmStatus updateUserAsSignupCompletion(String loginId, String oneTimeCode);
}
