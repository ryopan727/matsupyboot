package com.matsupy.cmn.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrMsg {
	/** メッセージID */
	private String msgId;
	/** メッセージ */
	private String message;
}
