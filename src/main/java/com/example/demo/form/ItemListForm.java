package com.example.demo.form;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * 商品一覧画面Formクラス
 * 
 * @author ys-fj
 *
 */
@Data
public class ItemListForm {

	/** 商品名 */
	@Length(max = 100)
	private String itemName;

	/** 入荷担当者 */
	@Length(max = 20)
	private String chargePerson;

	/** ユーザー一覧情報から選択されたログインID */
	private String selectedItemId;

	/**
	 * ユーザー一覧情報から選択されたログインIDをクリアします。
	 * 
	 * @return ユーザー一覧情報から選択されたログインIDクリア後のインスタンス
	 */
	public ItemListForm clearSelectedItemId() {
		this.selectedItemId = null;

		return this;
	}
}
