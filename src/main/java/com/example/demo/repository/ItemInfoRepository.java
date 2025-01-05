package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ItemInfo;

/**
 * 商品情報テーブルRepositoryクラス
 * 
 * @author ys-fj
 *
 */
public interface ItemInfoRepository extends JpaRepository<ItemInfo, String> {

	/**
	 * 商品名の部分一致検索を行います。
	 * 
	 * @param itemName 商品名
	 * @return 検索でヒットした商品情報のリスト
	 */
	List<ItemInfo> findByItemNameLike(String itemName);

	/**
	 * 商品IDの部分一致検索、入荷担当者の検索を行います。
	 * 
	 * @param itemName 商品名
	 * @param chargePerson 入荷担当者
	 * @return 検索でヒットした商品情報のリスト
	 */
	List<ItemInfo> findByItemNameLikeAndChargePerson(String itemName, String chargePerson);

}
