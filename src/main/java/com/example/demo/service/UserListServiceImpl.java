package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.constant.UserDeleteResult;
import com.example.demo.dto.UserListInfo;
import com.example.demo.dto.UserSearchInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー一覧画面Service実装クラス
 * 
 * @author ys-fj
 *
 */
@Service
@RequiredArgsConstructor
public class UserListServiceImpl implements UserListService {

	/** ユーザー情報テーブルDAO */
	private final UserInfoRepository repository;

	/** Dozer Mapper */
	private final Mapper mapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserListInfo> editUserList() {
		return toUserListInfos(repository.findAll());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserListInfo> editUserListByParam(UserSearchInfo dto) {
		return toUserListInfos(findUserInfoByParam(dto));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDeleteResult deleteUserInfoById(String loginId) {
		var userInfo = repository.findById(loginId);
		if (userInfo.isEmpty()) {
			return UserDeleteResult.ERROR;
		}

		repository.deleteById(loginId);

		return UserDeleteResult.SUCCEED;
	}

	/**
	 * ユーザー情報の条件検索を行い、検索結果を返却します。
	 * 
	 * @param form 入力情報
	 * @return 検索結果
	 */
	private List<UserInfo> findUserInfoByParam(UserSearchInfo dto) {
		var loginIdParam = AppUtil.addWildcard(dto.getLoginId());

		if (dto.getUserStatusKind() != null && dto.getAuthorityKind() != null) {
			return repository.findByLoginIdLikeAndUserStatusKindAndAuthorityKind(loginIdParam,
					dto.getUserStatusKind(), dto.getAuthorityKind());
		} else if (dto.getUserStatusKind() != null) {
			return repository.findByLoginIdLikeAndUserStatusKind(loginIdParam, dto.getUserStatusKind());
		} else if (dto.getAuthorityKind() != null) {
			return repository.findByLoginIdLikeAndAuthorityKind(loginIdParam, dto.getAuthorityKind());
		} else {
			return repository.findByLoginIdLike(loginIdParam);
		}
	}

	/**
	 * ユーザー情報EntityのListをユーザー一覧情報DTOのListに変換します。
	 * 
	 * @param userInfos ユーザー情報EntityのList
	 * @return ユーザ一覧情報DTOのList
	 */
	private List<UserListInfo> toUserListInfos(List<UserInfo> userInfos) {
		var userListInfos = new ArrayList<UserListInfo>();
		for (UserInfo userInfo : userInfos) {
			var userListInfo = mapper.map(userInfo, UserListInfo.class);
			userListInfo.setStatus(userInfo.getUserStatusKind().getDisplayValue());
			userListInfo.setAuthority(userInfo.getAuthorityKind().getDisplayValue());
			userListInfos.add(userListInfo);
		}

		return userListInfos;

	}

}
