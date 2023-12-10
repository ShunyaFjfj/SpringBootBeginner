package com.example.demo.constant;

/**
 * エラーメッセージID定数クラス
 * 
 * @author ys-fj
 *
 */
public class MessageConst {

	/** 共通：入力内容誤り */
	public static final String FORM_ERROR = "common.formError";

	/** ユーザー登録画面：既に本登録済み */
	public static final String SIGNUP_ALREADY_COMPLETED = "signup.alreadyCompleted";

	/** ユーザー登録画面：仮登録状態のログインID */
	public static final String SIGNUP_SIGNUP_PROCEEDING = "signup.signupProceeding";

	/** ユーザー登録画面：DB更新時エラー */
	public static final String SIGNUP_DB_ERROR = "signup.dbError";

	/** ユーザー登録画面：メール送信失敗 */
	public static final String SIGNUP_MAIL_SEND_ERROR = "signup.mailSendError";

	/** ユーザー登録画面：送信メール件名 */
	public static final String SIGNUP_MAIL_SUBJECT = "singup.mailSubject";

	/** ユーザー登録画面：送信メール本文 */
	public static final String SIGNUP_MAIL_TEXT = "singup.mailText";

	/** ユーザー登録画面：ユーザー仮登録完了 */
	public static final String SIGNUP_TENTATIVE_SUCCEED = "signup.tentativeSucceed";

	/** ユーザー登録情報確認画面：既に本登録済み */
	public static final String SIGNUP_CONFIRM_ALREADY_COMPLETED = "signupConfirm.alreadyCompleted";

	/** ユーザー登録情報確認画面：不正な画面操作 */
	public static final String SIGNUP_CONFIRM_INVALID_OPERATION = "signupConfirm.invalidOperation";

	/** ユーザー登録情報確認画面：仮登録されていない */
	public static final String SIGNUP_CONFIRM_NOT_EXISTS_TENTATIVE_USER = "signupConfirm.notExistsTentativeUser";

	/** ユーザー登録情報確認画面：ワンタイムコード誤り */
	public static final String SIGNUP_CONFIRM_WRONG_ONE_TIME_CODE = "signupConfirm.wrongOneTimeCode";

	/** ユーザー登録情報確認画面：ワンタイムコード有効期限切れ */
	public static final String SIGNUP_CONFIRM_EXPIRED_ONE_TIME_CODE = "signupConfirm.expiredOneTimeCode";

	/** ユーザー登録情報確認画面：DB更新時エラー */
	public static final String SIGNUP_CONFIRM_DB_ERROR = "signupConfirm.dbError";

	/** ユーザー登録情報確認画面：ユーザー本登録完了 */
	public static final String SIGNUP_CONFIRM_COMPLETE = "signupConfirm.complete";

	/** ユーザー一覧画面：存在しないログインID */
	public static final String USERLIST_NON_EXISTED_LOGIN_ID = "userList.nonExistedLoginId";

	/** ユーザー一覧画面：ユーザー削除完了 */
	public static final String USERLIST_DELETE_SUCCEED = "userList.deleteSucceed";

	/** ユーザー情報編集画面：存在しないログインID */
	public static final String USEREDIT_NON_EXISTED_LOGIN_ID = "userEdit.nonExistedLoginId";

	/** ユーザー情報編集画面：ユーザー更新失敗 */
	public static final String USEREDIT_UPDATE_FAILED = "userEdit.updateFailed";

	/** ユーザー情報編集画面：ユーザー更新完了 */
	public static final String USEREDIT_UPDATE_SUCCEED = "userEdit.updateSucceed";
}
