package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * ユーザー編集情報DTOクラス
 * 
 * @author ys-fj
 *
 */
@Data
public class UserEditInfo {

	/** ログインID */
	private String loginId;

	/** ログイン失敗回数 */
	private int loginFailureCount;

	/** アカウントロック日時 */
	private LocalDateTime accountLockedTime;
}
