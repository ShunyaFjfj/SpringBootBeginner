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

	/** ログイン画面：入力内容誤り */
	public static final String LOGIN_WRONG_INPUT = "login.wrongInput";

	/** ユーザー登録画面：既に登録されているログインID */
	public static final String SIGNUP_EXISTED_LOGIN_ID = "signup.existedLoginId";

	/** ユーザー登録画面：ユーザー登録完了 */
	public static final String SIGNUP_RESIST_SUCCEED = "signup.resistSucceed";

	/** ユーザー一覧画面：存在しないログインID */
	public static final String USERLIST_NON_EXISTED_LOGIN_ID = "userList.nonExistedLoginId";

	/** ユーザー一覧画面：ユーザー削除完了 */
	public static final String USERLIST_DELETE_SUCCEED = "userList.deleteSucceed";
}
