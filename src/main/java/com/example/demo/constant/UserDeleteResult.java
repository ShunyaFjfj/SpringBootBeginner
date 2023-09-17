package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 処理結果種別
 * 
 * @author ys-fj
 */
@Getter
@AllArgsConstructor
public enum UserDeleteResult {

	/* エラーなし */
	SUCCEED(MessageConst.USERLIST_DELETE_SUCCEED),

	/* エラーあり */
	ERROR(MessageConst.USERLIST_NON_EXISTED_LOGIN_ID);

	/** メッセージID */
	private String messageId;

}
