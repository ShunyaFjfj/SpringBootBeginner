package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.constant.MessageConst;
import com.example.demo.constant.SessionKeyConst;
import com.example.demo.constant.SignupResult;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.dto.SignupInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.service.SignupService;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * ユーザー登録画面Controllerクラス
 * 
 * @author ys-fj
 *
 */
@Controller
@RequiredArgsConstructor
public class SignupController {

	/** ユーザー登録画面Serviceクラス */
	private final SignupService service;

	/** メッセージソース */
	private final MessageSource messageSource;

	/** オブジェクト間項目輸送クラス */
	private final Mapper mapper;

	/** セッションオブジェクト */
	private final HttpSession session;

	/** 画面で使用するフォームクラス名 */
	private static final String FORM_CLASS_NAME = "signupForm";

	/**
	 * 画面の初期表示を行います。
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @return ユーザー登録画面
	 */
	@GetMapping(UrlConst.SIGNUP)
	public String view(Model model) {
		var isInitialDisp = !model.containsAttribute(FORM_CLASS_NAME);
		if (isInitialDisp) {
			model.addAttribute(FORM_CLASS_NAME, new SignupForm());
		}
		return ViewNameConst.SIGNUP;
	}

	/**
	 * 画面の入力情報からユーザー登録処理を呼び出します。
	 * 
	 * <p>ただし、入力チェックでエラーになった場合や登録済みのログインIDを使っていた場合は<br>
	 * エラーメッセージを画面に表示します。
	 * 
	 * @param form 入力情報
	 * @param bdResult 入力内容の単項目チェック結果
	 * @param redirectAttributes リダイレクト用モデル
	 */
	@PostMapping(UrlConst.SIGNUP)
	public String signup(@Validated SignupForm form, BindingResult bdResult, RedirectAttributes redirectAttributes) {
		if (bdResult.hasErrors()) {
			editGuideMessage(form, bdResult, MessageConst.FORM_ERROR, redirectAttributes);
			return AppUtil.doRedirect(UrlConst.SIGNUP);
		}

		var signupResult = service.signup(mapper.map(form, SignupInfo.class));
		var isError = signupResult != SignupResult.SUCCEED;
		if (isError) {
			editGuideMessage(form, bdResult, signupResult.getMessageId(), redirectAttributes);
			return AppUtil.doRedirect(UrlConst.SIGNUP);
		}

		session.setAttribute(SessionKeyConst.ONE_TIME_AUTH_LOGIN_ID, form.getLoginId());
		return AppUtil.doRedirect(UrlConst.SIGNUP_CONFIRM);
	}

	/**
	 * メッセージIDを使ってプロパティファイルからメッセージを取得し、画面に表示します。
	 * 
	 * <p>また、画面でメッセージを表示する際に通常メッセージとエラーメッセージとで色分けをするため、<br>
	 * その判定に必要な情報も画面に渡します。
	 * 
	 * @param form 入力情報
	 * @param bdResult 入力内容の単項目チェック結果
	 * @param messageId プロパティファイルから取得したいメッセージのID
	 * @param redirectAttributes リダイレクト用モデル
	 */
	private void editGuideMessage(SignupForm form, BindingResult bdResult, String messageId,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", AppUtil.getMessage(messageSource, messageId));
		redirectAttributes.addFlashAttribute("isError", true);
		redirectAttributes.addFlashAttribute(form);
		redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + FORM_CLASS_NAME, bdResult);
	}

}
