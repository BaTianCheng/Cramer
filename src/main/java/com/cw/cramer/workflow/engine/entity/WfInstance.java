package com.cw.cramer.workflow.engine.entity;

import java.io.Serializable;

/**
 * WfInstance
 * @author wicks
 */
public class WfInstance implements Serializable{

	private static final long serialVersionUID = -4450133659706649050L;
	
	/**
	 * 实例编号
	 */
	protected String instanceId;
	
	/**
	 * 业务Key
	 */
	protected String businessKey;
	
	/**
	 * 模板Key
	 */
	protected String templateKey;
	
	/**
	 * 模板名称
	 */
	protected String templateName;
	
	/**
	 * 定义编号
	 */
	protected String definedId;
	
	/**
	 * 开始时间
	 */
	protected String startTime;
	
	/**
	 * 结束时间
	 */
	protected String endTime;
	
	/**
	 * 操作人
	 */
	protected String operate;
	
	/**
	 * 流程结果
	 */
	protected String result;
	
	/**
	 * 流程状态，000 运行中，100 挂起，200 结束，201中止
	 */
	protected String status;
	
	/**
	 * 描述
	 */
	protected String description;
	
	/**
	 * 发起人
	 */
	protected String creator;

	
	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getTemplateKey() {
		return templateKey;
	}

	public void setTemplateKey(String templateKey) {
		this.templateKey = templateKey;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getDefinedId() {
		return definedId;
	}

	public void setDefinedId(String definedId) {
		this.definedId = definedId;
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

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

}
