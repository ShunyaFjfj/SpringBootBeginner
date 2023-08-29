package com.example.demo.constant;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ユーザー権限種別
 * 
 * @author ys-fj
 */
@Getter
@AllArgsConstructor
public enum AuthorityKind {

	/* 登録内容が不正 */
	UNKNOWN("", "登録内容が不正"),

	/* 商品情報の確認が可能 */
	ITEM_WATCHER("1", "商品情報の確認が可能"),

	/* 商品情報の確認、更新が可能 */
	ITEM_MANAGER("2", "商品情報の確認、更新が可能"),

	/* 商品情報の確認、更新、全ユーザー情報の管理が可能 */
	ITEM_AND_USER_MANAGER("3", "商品情報の確認、更新、全ユーザー情報の管理が可能");

	/** コード値 */
	private String code;

	/** 画面表示する説明文 */
	private String displayValue;

	/**
	 * Enum逆引き
	 * 
	 * 権限種別からEnumを逆引きします。
	 * 
	 * @param code 権限種別コード値
	 * @return 引数の権限種別コード値に対応するEnum、ただし見つからなかった場合はUNKNOWN
	 */
	public static AuthorityKind from(String code) {
		return Arrays.stream(AuthorityKind.values())
				.filter(authorityKind -> authorityKind.getCode().equals(code))
				.findFirst()
				.orElse(UNKNOWN);
	}
}
