package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.core.Conventions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.dto.ItemSearchInfo;
import com.example.demo.form.ItemListForm;
import com.example.demo.service.ItemListService;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

/**
 * 商品一覧画面Controllerクラス
 * 
 * @author ys-fj
 *
 */
@Controller
@RequiredArgsConstructor
public class ItemListController {

	/** 商品一覧画面Serviceクラス */
	private final ItemListService service;

	/** Dozer Mapper */
	private final Mapper mapper;

	/** メッセージソース */
	private final MessageSource messageSource;

	/** モデルキー：ユーザー情報リスト */
	private static final String KEY_ITEMLIST = "itemList";

	/** モデルキー：ユーザーIDリスト */
	private static final String KEY_USER_ID_OPTIONS = "userIdOptions";

	/**
	 * 画面の初期表示を行います。
	 * 
	 * <p>またその際、画面選択項目「アカウント状態」「所有権限」の選択肢を生成して画面に渡します。
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @return ユーザー一覧画面テンプレート名
	 */
	@GetMapping(UrlConst.ITEM_LIST)
	public String view(Model model, ItemListForm form) {
		var userIdNames = service.obtainUserIdList();
		model.addAttribute(KEY_USER_ID_OPTIONS, userIdNames);

		return ViewNameConst.ITEM_LIST;
	}

	/**
	 * 初期表示、検索後や削除後のリダイレクトによる再表示のいずれかかを判定して画面に表示する一覧情報を作成します。
	 * 
	 * @param model モデル
	 * @return ユーザー一覧情報
	 */
	//	@SuppressWarnings("unchecked")
	//	private List<UserListInfo> editUserListInfo(Model model) {
	// TODO 実装途中
	//		var doneSearchOrDelete = model.containsAttribute(KEY_OPERATION_KIND);
	//		if (doneSearchOrDelete) {
	//			var operationKind = (OperationKind) model.getAttribute(KEY_OPERATION_KIND);
	//			if (operationKind == OperationKind.SEARCH) {
	//				return (List<UserListInfo>) model.getAttribute(KEY_USERLIST);
	//			}
	//			if (operationKind == OperationKind.DELETE) {
	//				var searchDto = mapper.map((UserListForm) model.getAttribute(KEY_USERLIST_FORM), UserSearchInfo.class);
	//				return service.editUserListByParam(searchDto);
	//			}
	//		}
	//
	//		return service.editUserList();
	//	}

	/**
	 * 検索条件に合致するユーザー情報を画面に表示します。
	 * 
	 * @param form 入力情報
	 * @param redirectAttributes リダイレクト用オブジェクト
	 * @return リダイレクトURL
	 */
	@PostMapping(value = UrlConst.ITEM_LIST, params = "search")
	public String searchUser(ItemListForm form, RedirectAttributes redirectAttributes) {
		var searchDto = mapper.map(form, ItemSearchInfo.class);
		var itemInfos = service.editItemListByParam(searchDto);
		redirectAttributes.addFlashAttribute(KEY_ITEMLIST, itemInfos);
		redirectAttributes.addFlashAttribute(Conventions.getVariableName(form), form);

		return AppUtil.doRedirect(UrlConst.ITEM_LIST);
	}

	/**
	 * 選択行のユーザー情報を削除して、最新情報で画面を再表示します。
	 * 
	 * @param form 入力情報
	 * @return リダイレクトURL
	 */
	@PostMapping(value = UrlConst.ITEM_LIST, params = "edit")
	public String updateUser(ItemListForm form) {
		// TODO 実装途中
		//		session.setAttribute(SessionKeyConst.SELECETED_LOGIN_ID, form.getSelectedLoginId());
		return AppUtil.doRedirect(UrlConst.ITEM_LIST);
	}

	/**
	 * 選択行のユーザー情報を削除して、最新情報で画面を再表示します。
	 * 
	 * @param form 入力情報
	 * @param redirectAttributes リダイレクト用オブジェクト
	 * @return リダイレクトURL
	 */
	@PostMapping(value = UrlConst.ITEM_LIST, params = "delete")
	public String deleteUser(ItemListForm form, RedirectAttributes redirectAttributes) {
		// TODO 実装途中
		//		var executeResult = service.deleteUserInfoById(form.getSelectedLoginId());
		//		redirectAttributes.addFlashAttribute(ModelKey.IS_ERROR, executeResult == UserDeleteResult.ERROR);
		//		redirectAttributes.addFlashAttribute(ModelKey.MESSAGE,
		//				AppUtil.getMessage(messageSource, executeResult.getMessageId()));
		//		// 削除後、フォーム情報の「選択されたログインID」は不要になるため、クリアします。
		//		redirectAttributes.addFlashAttribute(KEY_USERLIST_FORM, form.clearSelectedLoginId());
		//		redirectAttributes.addFlashAttribute(KEY_OPERATION_KIND, OperationKind.DELETE);
		//
		return AppUtil.doRedirect(UrlConst.ITEM_LIST);
	}

	/**
	 * 操作種別Enum
	 * 
	 * @author ys-fj
	 */
	public enum OperationKind {
		SEARCH, DELETE
	}
}
