package com.cw.cramer.sys.thread;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.cw.cramer.common.constant.CommonConstant;
import com.cw.cramer.sys.entity.SysOperateLog;
import com.cw.cramer.sys.thread.runnable.OperateLogRunnable;

/**
 * 操作日志线程
 * @author wicks
 */
public class OperateLogThread extends Thread{
	
	/**
	 * 构造线程
	 * @param operateLogRunnable
	 */
	public OperateLogThread(OperateLogRunnable operateLogRunnable) {
		super(operateLogRunnable);
	}

	/**
	 * 开启线程
	 */
	public void start(){
		CommonConstant.logQueue = new ConcurrentLinkedQueue<SysOperateLog>();
		super.start();
	}
	
	/**
	 * 加入队列
	 * @param log
	 */
	public void insertQueue(SysOperateLog log){
		if(CommonConstant.logQueue != null){
			CommonConstant.logQueue.add(log);
			synchronized(this){
				this.notify();
			}
		}
	}
	
}
