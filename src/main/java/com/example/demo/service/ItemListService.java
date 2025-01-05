package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ItemSearchInfo;
import com.example.demo.dto.StaffInfo;
import com.example.demo.entity.ItemInfo;

/**
 * 商品一覧画面Serviceインターフェース
 * 
 * @author ys-fj
 *
 */
public interface ItemListService {

	/**
	 * ユーザー情報テーブルを全件検索し、ユーザーIDとユーザー名の一覧を返却します。
	 * 
	 * @return ユーザー情報テーブルに登録されているユーザーIDとユーザー名のリスト
	 */
	public List<StaffInfo> obtainUserIdList();

	/**
	 * 商品情報テーブルを条件検索した結果を返却します。
	 * 
	 * @param dto 検索に使用するパラメーター
	 * @return 検索結果
	 */
	public List<ItemInfo> editItemListByParam(ItemSearchInfo dto);
}
