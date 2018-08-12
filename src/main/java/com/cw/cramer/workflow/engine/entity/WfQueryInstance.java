package com.cw.cramer.workflow.engine.entity;

import java.io.Serializable;

/**
 * 工作流实例查询类
 * @author wicks
 */
public class WfQueryInstance implements Serializable{

	private static final long serialVersionUID = 5926376210082891613L;
	
	/**
	 * 页码
	 */
	private int pageNum;
	
	/**
	 * 页大小
	 */
	private int pageSize;
	
	/**
	 * 发起人
	 */
	private String creator;
	
	/**
	 * 开始时间
	 */
	private String startTime;
	
	/**
	 * 结束时间
	 */
	private String endTime;
	
	/**
	 * 模板
	 */
	private String templateKey;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTemplateKey() {
		return templateKey;
	}

	public void setTemplateKey(String templateKey) {
		this.templateKey = templateKey;
	}

}
