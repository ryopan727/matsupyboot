package com.matsupy.cmn.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private static final String PATTERN = "yyyy/MM/dd HH:mm:ss";

	/**
	 * Date を yyyy/MM/dd HH:mm:ss 形式に変換
	 */
	public static String format(Date date) {
		if (date == null)
			return null;
		return new SimpleDateFormat(PATTERN).format(date);
	}
}