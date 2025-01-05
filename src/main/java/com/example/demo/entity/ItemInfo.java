package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品情報テーブルEntityクラス
 * 
 * @author ys-fj
 *
 */
@Entity
@Table(name = "item_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemInfo {

	/** 商品ID */
	@Id
	@Column(name = "item_id")
	private String itemId;

	/** 商品名 */
	@Column(name = "item_name")
	private String itemName;

	/** 単価 */
	private int price;

	/** 入荷日 */
	@Column(name = "arrival_date")
	private LocalDate arrivalDate;

	/** 入荷担当者 */
	@Column(name = "charge_person")
	private String chargePerson;

	/** 登録日時 */
	@Column(name = "create_time")
	private LocalDateTime createTime;

	/** 最終更新日時 */
	@Column(name = "update_time")
	private LocalDateTime updateTime;

	/** 最終更新ユーザ */
	@Column(name = "update_user")
	private String updateUser;

}
