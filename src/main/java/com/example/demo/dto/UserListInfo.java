package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * ユーザー一覧画面DTOクラス
 * 
 * @author ys-fj
 *
 */
@Data
public class UserListInfo {

	/** ログインID */
	private String loginId;

	/** ログイン失敗回数 */
	private int loginFailureCount;

	/** アカウントロック日時 */
	private LocalDateTime accountLockedTime;

	/** アカウント状態 */
	private String status;

	/** 権限 */
	private String authority;

	/** 登録日時 */
	private LocalDateTime createTime;

	/** 最終更新日時 */
	private LocalDateTime updateTime;

	/** 最終更新ユーザ */
	private String updateUser;

}
