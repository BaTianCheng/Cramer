package com.cw.cramer.common.constant;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.cw.cramer.sys.entity.SysOperateLog;
import com.cw.cramer.sys.thread.OperateLogThread;
import com.cw.cramer.sys.thread.runnable.OperateLogRunnable;

import cn.hutool.setting.dialect.Props;

/**
 * 通用常量
 * @author wicks
 */
public class CommonConstant {
	
	/**
	 * 配置文件路径
	 */
	private static final String XMLPATH = "application.properties";
	
	/**
	 * 配置常量
	 */
	public static final Map<String, String> CONFIG_PARAMS;
	
	/**
	 * 日志记录线程
	 */
	public static OperateLogThread logThread;
	
	static{
		//初始化系统配置
	    Props props = new Props(XMLPATH);
		CONFIG_PARAMS=new HashMap<>();
		CONFIG_PARAMS.put("ESB_PATH", props.getProperty("ESB_PATH"));
		
		//启动系统线程
		logThread = new OperateLogThread(new OperateLogRunnable());
		logThread.start();
	}
	
	/**
	 * 升序排序
	 */
	public static final String SORT_ASC = "asc";
	
	/**
	 * 降序排序
	 */
	public static final String SORT_DESC = "desc";
	
	/**
	 * 日志队列
	 */
	public static ConcurrentLinkedQueue<SysOperateLog> logQueue;
	
	/**
	 * 日志周期
	 */
	public static final long LOG_TIME = 1000*60*1;
	
	/**
	 * 日志队列最大长度
	 */
	public static final long LOGQUEUE_SIZE = 100;

}
