package com.matsupy.cmn.dto;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

import com.matsupy.cmn.constant.ResultCd;
import com.matsupy.cmn.exception.ErrMsg;

import lombok.Data;

@Data
public class MatsupyContext {
	private Object outputDto;

	private Object inputDto;

	private MultiValueMap<String, String> resHeader;

	private HttpStatus status;

	private ResultCd result;

	/** エラーメッセージ情報 */
	private List<ErrMsg> errMsgLst;

	public void setError(HttpStatus status, ResultCd result, String msgId) {
		this.status = status;
		this.result = result;
		ErrMsg errMsg = new ErrMsg(msgId, "", new Object[0]);
		this.errMsgLst = List.of(errMsg);
	}

	public void setError(HttpStatus status, ResultCd result, List<ErrMsg> errMsgLst) {
		this.status = status;
		this.result = result;
		this.errMsgLst = errMsgLst;
	}

}
