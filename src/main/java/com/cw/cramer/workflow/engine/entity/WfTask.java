package com.cw.cramer.workflow.engine.entity;

import java.io.Serializable;
import java.util.List;

/**
 * WfTaskHistory
 * @author wicks
 */
public class WfTask implements Serializable{

	private static final long serialVersionUID = -1657002425940477762L;
	
	/**
	 * 实例编号
	 */
	protected String instanceId;
	
	/**
	 * 任务编号
	 */
	protected String taskId;
	
	/**
	 * 任务key
	 */
	protected String taskKey;
	
	/**
	 * 任务名称
	 */
	protected String taskName;
	
	/**
	 * 开始
	 */
	protected String startTime;
	
	/**
	 * 完成时间
	 */
	protected String endTime;
	
	/**
	 * 处理人
	 */
	protected String assigne;
	
	/**
	 * 表单内容
	 */
	protected List<WfFormField> formFields;
	
	/**
	 * 描述
	 */
	protected String description;
	
	/**
	 * 工作流实例
	 */
	protected WfInstance wfInstance;
	

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskKey() {
		return taskKey;
	}

	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
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

	public String getAssigne() {
		return assigne;
	}

	public void setAssigne(String assigne) {
		this.assigne = assigne;
	}

	public List<WfFormField> getFormFields() {
		return formFields;
	}

	public void setFormFields(List<WfFormField> formFields) {
		this.formFields = formFields;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public WfInstance getWfInstance() {
		return wfInstance;
	}

	public void setWfInstance(WfInstance wfInstance) {
		this.wfInstance = wfInstance;
	}

}
