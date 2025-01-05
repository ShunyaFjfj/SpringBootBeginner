package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ItemSearchInfo;
import com.example.demo.dto.StaffInfo;
import com.example.demo.entity.ItemInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.repository.ItemInfoRepository;
import com.example.demo.repository.UserInfoRepository;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

/**
 * 商品一覧画面Service実装クラス
 * 
 * @author ys-fj
 *
 */
@Service
@RequiredArgsConstructor
public class ItemListServiceImpl implements ItemListService {

	/** ユーザー情報テーブルDAO */
	private final UserInfoRepository userInfoRepository;

	/** 商品情報テーブルDAO */
	private final ItemInfoRepository itemInfoRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<StaffInfo> obtainUserIdList() {
		var userInfos = userInfoRepository.findAll();

		List<StaffInfo> staffInfos = new ArrayList<>();
		for (UserInfo userInfo : userInfos) {
			var staffInfo = new StaffInfo();
			staffInfo.setUserId(userInfo.getLoginId());
			staffInfo.setUserName(userInfo.getUserName());
			staffInfos.add(staffInfo);
		}

		return staffInfos;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ItemInfo> editItemListByParam(ItemSearchInfo dto) {
		// あいまい検索用の商品名
		String itemName = "%" + dto.getItemName() + "%";

		// 入荷担当者が選択されていない時
		if (StringUtils.isEmpty(dto.getChargePerson())) {
			return itemInfoRepository.findByItemNameLike(itemName);
		} else {
			return itemInfoRepository.findByItemNameLikeAndChargePerson(itemName, dto.getChargePerson());
		}

	}

}
