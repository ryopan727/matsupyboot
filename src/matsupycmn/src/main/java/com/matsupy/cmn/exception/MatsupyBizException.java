package com.matsupy.cmn.exception;

import java.util.List;

import com.matsupy.cmn.constant.MtpHttpStatus;

public class MatsupyBizException extends Exception{

	private static final long serialVersionUID = 1L;

	private int status;

	private String errCd;
	
	private List<ErrInfo> errInfos;

	public MatsupyBizException(String err) {
		super(err);
		status = MtpHttpStatus.INTERNAL_SERVER_ERROR.getCode();
	}
	
	public MatsupyBizException(String err, Throwable e) {
		super(err, e);
		status = MtpHttpStatus.INTERNAL_SERVER_ERROR.getCode();
	}
	
	public MatsupyBizException(MtpHttpStatus status, String err, String errCd, String msgId) {
		super(err);
		this.errCd = errCd;
		this.status = status.getCode();
		ErrInfo errInfo = new ErrInfo(errCd,msgId,null);
		errInfos = List.of(errInfo);
	}
	
	public MatsupyBizException(MtpHttpStatus status, String err, List<ErrInfo> errInfos) {
		super(err);
		this.status = status.getCode();
		this.errInfos = errInfos;
		this.errCd = "1";
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public String getErrCd() {
		return this.errCd;
	}

	public List<ErrInfo> getErrInfo() {
		return this.errInfos;
	}

}
