package com.example.demo.dto;

import lombok.Data;

/**
 * ユーザー登録用DTOクラス
 * 
 * @author ys-fj
 *
 */
@Data
public class SignupInfo {

	/** ログインID */
	private String loginId;

	/** パスワード */
	private String password;

	/** メールアドレス */
	private String mailAddress;

}
