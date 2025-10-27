package com.matsupy.cmn.exception;

public class MatsupyBizException extends Exception {

	private static final long serialVersionUID = 1L;

	public MatsupyBizException(String err) {
		super(err);
	}

	public MatsupyBizException(String err, Throwable e) {
		super(err, e);
	}

}
