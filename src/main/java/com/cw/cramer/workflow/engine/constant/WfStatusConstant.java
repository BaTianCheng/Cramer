package com.cw.cramer.workflow.engine.constant;

/**
 * 状态常量
 * @author wicks
 */
public class WfStatusConstant {

    //000 运行中，100 挂起，200 结束，201中止 
    
	/**
	 * 运行中
	 */
	public static final String STATUS_RUNNING = "000";
	
    /**
     * 挂起
     */
    public static final String STATUS_SUSPEND = "100";
	
    /**
     * 运行中
     */
    public static final String STATUS_END = "200";
    
    /**
     * 运行中
     */
    public static final String STATUS_ABORT = "201";
}
