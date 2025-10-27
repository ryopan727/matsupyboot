package com.matsupy.cmn.constant;

public enum ResultCd {
	NORMAL("0"), ERROR("1"), CHK_ERROR("2");

	private final String value;

	private ResultCd(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
