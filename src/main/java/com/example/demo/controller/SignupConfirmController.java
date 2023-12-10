package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.constant.MessageConst;
import com.example.demo.constant.ModelKey;
import com.example.demo.constant.SessionKeyConst;
import com.example.demo.constant.SignupConfirmStatus;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.service.SignupConfirmService;
import com.example.demo.util.AppUtil;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * ユーザー登録情報確認画面Controllerクラス
 * 
 * @author ys-fj
 *
 */
@Controller
@RequiredArgsConstructor
public class SignupConfirmController {

	/** ユーザー登録情報確認画面Serviceクラス */
	private final SignupConfirmService service;

	/** メッセージソース */
	private final MessageSource messageSource;

	/** セッションオブジェクト */
	private final HttpSession session;

	/**
	 * 画面の初期表示を行います。
	 * 
	 * @param oneTimeCode ワンタイムコード
	 * @param redirectAttributes リダイレクト用モデル
	 * @return ユーザー本登録完了画面テンプレート名
	 */
	@GetMapping(UrlConst.SIGNUP_CONFIRM)
	public String view() {
		return ViewNameConst.SIGNUP_CONFIRM;
	}

	/**
	 * 画面に入力されたワンタイムコードの認証を行います。
	 * 
	 * @param oneTimeCode 入力されたワンタイムコード
	 * @param redirectAttributes リダイレクト用オブジェクト
	 * @return リダイレクトURL
	 */
	@PostMapping(UrlConst.SIGNUP_CONFIRM)
	public String signupConfirm(String oneTimeCode, RedirectAttributes redirectAttributes) {
		var loginId = (String) session.getAttribute(SessionKeyConst.ONE_TIME_AUTH_LOGIN_ID);
		if (loginId == null) {
			redirectAttributes.addFlashAttribute(ModelKey.MESSAGE, MessageConst.SIGNUP_CONFIRM_INVALID_OPERATION);
			redirectAttributes.addFlashAttribute(ModelKey.IS_ERROR, true);
			return AppUtil.doRedirect(UrlConst.SIGNUP_CONFIRM);
		}

		var signupConfirmStatus = service.updateUserAsSignupCompletion(loginId, oneTimeCode);

		// 次画面にワンタイムコード認証結果の情報を渡す
		var message = AppUtil.getMessage(messageSource, signupConfirmStatus.getMessageId());
		var isError = signupConfirmStatus != SignupConfirmStatus.SUCCEED;
		redirectAttributes.addFlashAttribute(ModelKey.MESSAGE, message);
		redirectAttributes.addFlashAttribute(ModelKey.IS_ERROR, isError);
		if (isError) {
			return AppUtil.doRedirect(UrlConst.SIGNUP_CONFIRM);
		}

		session.removeAttribute(SessionKeyConst.ONE_TIME_AUTH_LOGIN_ID);
		return AppUtil.doRedirect(UrlConst.SIGNUP_COMPLETION);
	}

}
