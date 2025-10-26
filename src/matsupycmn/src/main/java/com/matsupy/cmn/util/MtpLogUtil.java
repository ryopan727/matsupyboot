package com.matsupy.cmn.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * まつーぴーログ
 */
@Component
public class MtpLogUtil {
    private final Logger log = LoggerFactory.getLogger(MtpLogUtil.class);

    public void info(String msg) {
        log.info(msg);
    }
    
    public void startLog(String methodName) {
    	log.debug("[start]" + methodName);
    }

    public void endLog(String methodName) {
    	log.debug("[end]" + methodName);
    }

    public void error(String msg, Throwable e) {
        log.error(msg, e);
    }
}
