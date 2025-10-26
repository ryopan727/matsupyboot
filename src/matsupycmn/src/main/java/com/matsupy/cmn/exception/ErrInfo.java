package com.matsupy.cmn.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 内部エラー情報クラス。
 */
public class ErrInfo {
	/** エラーコード */
	private String errCd;
	/** メッセージID */
	private String msgId;
	/** メッセージ */
	private String message;
}
