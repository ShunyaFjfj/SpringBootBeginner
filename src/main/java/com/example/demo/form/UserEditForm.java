package com.example.demo.form;

import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.db.UserStatusKind;

import lombok.Data;

/**
 * ユーザー編集画面Formクラス
 * 
 * @author ys-fj
 *
 */
@Data
public class UserEditForm {

	/** ログイン失敗状況をリセットするか(リセットするならtrue) */
	private boolean resetsLoginFailure;

	/** アカウント状態種別 */
	private UserStatusKind userStatusKind;

	/** ユーザー権限種別 */
	private AuthorityKind authorityKind;

}
