package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ユーザー本登録結果種別
 * 
 * @author ys-fj
 */
@Getter
@AllArgsConstructor
public enum SignupConfirmStatus {

	/* 本更新成功 */
	SUCCEED(MessageConst.SIGNUP_CONFIRM_COMPLETE),

	/* 仮登録状態のユーザーが存在しない */
	FAILURE_BY_NOT_EXISTS_TENTATIVE_USER(MessageConst.SIGNUP_CONFIRM_NOT_EXISTS_TENTATIVE_USER),

	/* ワンタイムコード誤り */
	FAILURE_BY_WRONG_ONE_TIME_CODE(MessageConst.SIGNUP_CONFIRM_WRONG_ONE_TIME_CODE),

	/* ワンタイムコード有効期限切れ */
	FAILURE_BY_EXPIRED_ONE_TIME_CODE(MessageConst.SIGNUP_CONFIRM_EXPIRED_ONE_TIME_CODE),

	/* DB更新エラー */
	FAILURE_BY_DB_ERROR(MessageConst.SIGNUP_DB_ERROR);

	String messageId;

}
