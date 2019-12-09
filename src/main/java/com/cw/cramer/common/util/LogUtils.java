package com.cw.cramer.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LogUtils {
	
	private static final Logger logger = LoggerFactory.getLogger("com.cw.cramer");
	
	/**
	 * 记录日志信息
	 * @param info
	 */
	public static void info(String info){
		logger.info(info);
	}
	
	/**
     * 记录警告信息
     * @param info
     */
    public static void warn(String info){
        logger.warn(info);
    }
	
	/**
	 * 记录错误日志
	 * @param info
	 */
	public static void error(String info){
		logger.error(info);
	}

	/**
	 * 记录错误日志
	 * @param info
	 */
	public static void error(String info, Throwable t){
		logger.error(info, t);
	}
	
}
