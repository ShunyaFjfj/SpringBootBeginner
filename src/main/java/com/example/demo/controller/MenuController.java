package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.constant.db.AuthorityKind;

/**
 * メニュー画面Controllerクラス
 * 
 * @author ys-fj
 *
 */
@Controller
public class MenuController {

	/**
	 * 画面の初期表示を行います。
	 * 
	 * <p>その際、ユーザー情報から権限を確認し、ユーザー管理が可能かどうかを判定した結果を画面に渡します。
	 * 
	 * @param user 認証済みユーザー情報
	 * @param model モデル
	 * @return メニュー画面
	 */
	@GetMapping(UrlConst.MENU)
	public String view(@AuthenticationPrincipal User user, Model model) {
		var hasUserManageAuth = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority()
						.equals(AuthorityKind.ITEM_AND_USER_MANAGER.getCode()));
		model.addAttribute("hasUserManageAuth", hasUserManageAuth);

		return ViewNameConst.MENU;
	}

}
