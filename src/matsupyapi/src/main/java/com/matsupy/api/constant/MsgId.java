package com.matsupy.api.constant;

public enum MsgId {
	MSGE001("E400001"),
	MSGE002("E400002"),
	MSGE003("E400003");
	
	MsgId(String value) {
		this.value = value;
	}

	private String value;
	
	public String getValue() {
		return this.value;
	}
}
