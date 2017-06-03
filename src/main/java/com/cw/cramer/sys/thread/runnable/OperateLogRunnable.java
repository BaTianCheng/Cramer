package com.cw.cramer.sys.thread.runnable;

import com.cw.cramer.common.constant.CommonConstant;
import com.cw.cramer.common.util.LogUtils;
import com.cw.cramer.common.util.SpringBeanUtils;
import com.cw.cramer.sys.service.SysOperateLogService;

/**
 * 操作日志
 * @author wicks
 */
public class OperateLogRunnable implements Runnable{

	/**
	 * 上次执行时间
	 */
	private long lastTime;
	
	/**
	 * 是否停用标记
	 */
	private boolean stopFlag;
	
	/**
	 * 操作日志服务
	 */
	private SysOperateLogService sysOperateLogService;
	
	/**
	 * 构造函数（自动注入Spring）
	 */
	public OperateLogRunnable(){  
        this.sysOperateLogService =  (SysOperateLogService)SpringBeanUtils.getBeanByName("sysOperateLogService");
    }  
	
	@Override
	public void run() {
		this.stopFlag = false;
		lastTime = System.currentTimeMillis();
		
		while(!stopFlag){
			if(getQueueSize()>=CommonConstant.LOGQUEUE_SIZE || System.currentTimeMillis()-lastTime>CommonConstant.LOG_TIME){
				lastTime = System.currentTimeMillis();
				LogUtils.info("线程启动——插入操作记录");
				synchronized (this) {
					while(!CommonConstant.logQueue.isEmpty()){
						sysOperateLogService.insert(CommonConstant.logQueue.poll());
					}
				}
			} else {
				try {
					synchronized(this){
						this.wait(CommonConstant.LOG_TIME-(System.currentTimeMillis()-lastTime)>0?CommonConstant.LOG_TIME-(System.currentTimeMillis()-lastTime):1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * 设置停用标记
	 * @param stopFlag
	 */
	public void setStopFlag(boolean stopFlag) {
		this.stopFlag = stopFlag;
	}
	
	/**
	 * 获取停用标记
	 * @param stopFlag
	 */
	public boolean getStopFlag() {
		return stopFlag;
	}
	
	/**
	 * 获取队列的大小
	 * @return
	 */
	public int getQueueSize(){
		if(CommonConstant.logQueue != null){
			return CommonConstant.logQueue.size();
		} else {
			return 0;
		}
	}

}
