package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserListInfo;

/**
 * ユーザー一覧画面Serviceクラス
 * 
 * @author ys-fj
 *
 */
public interface UserListService {

	/**
	 * ユーザー情報テーブルを全件検索し、ユーザー一覧画面に必要な情報へ変換して返却します。
	 * 
	 * @return ユーザー情報テーブルの全登録情報
	 */
	public List<UserListInfo> editUserList();

}
