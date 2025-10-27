package com.matsupy.cmn.util;

import org.springframework.stereotype.Component;

import com.matsupy.cmn.constant.MatsupyLogger;

/**
 * まつーぴーログ
 */
@Component
public class MtpLogUtil {
	public void info(String msg) {
		MatsupyLogger.APP_LOGGER.info(msg);
	}

	public void startLog(String methodName) {
		MatsupyLogger.APP_LOGGER.debug("[start]" + methodName);
	}

	public void endLog(String methodName) {
		MatsupyLogger.APP_LOGGER.debug("[end]" + methodName);
	}

	public void error(String msg, Throwable e) {
		MatsupyLogger.APP_LOGGER.error(msg, e);
	}
}
