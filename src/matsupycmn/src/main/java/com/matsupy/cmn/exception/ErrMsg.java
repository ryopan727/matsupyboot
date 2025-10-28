package com.matsupy.cmn.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrMsg {
	/** メッセージID */
	private String msgId;
	/** フィールド名 */
	private String field;
	/** 埋め込み文字 */
	private Object[] prm;

}
