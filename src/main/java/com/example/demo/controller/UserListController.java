package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.UserStatusKind;
import com.example.demo.form.UserListForm;

/**
 * ユーザー一覧画面Controllerクラス
 * 
 * @author ys-fj
 *
 */
@Controller
public class UserListController {

	/** モデルキー：ユーザー情報リスト */
	private static final String KEY_USER_STATUS_KINDS = "userStatusKinds";

	/** モデルキー：ユーザー情報リスト */
	private static final String KEY_AUTHORITY_KINDS = "authorityKinds";

	/**
	 * 画面の初期表示を行います。
	 * 
	 * <p>またその際、画面選択項目「アカウント状態」「所有権限」の選択肢を生成して渡します。
	 * 
	 * @param model モデル
	 * @return 表示画面
	 */
	@GetMapping(UrlConst.USER_LIST)
	public String view(Model model, UserListForm form) {
		model.addAttribute(KEY_USER_STATUS_KINDS, UserStatusKind.values());
		model.addAttribute(KEY_AUTHORITY_KINDS, AuthorityKind.values());

		return "userList";
	}

}
