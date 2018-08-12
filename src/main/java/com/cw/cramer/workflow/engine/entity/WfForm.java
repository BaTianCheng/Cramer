package com.cw.cramer.workflow.engine.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 工作流表单
 * @author wicks
 */
public class WfForm implements Serializable{

	private static final long serialVersionUID = 4633921744949682919L;
	
	/**
	 * 部署编号
	 */
	protected String deploymentId;
	
	/**
	 * 表单外部键
	 */
	protected String formKey;
	
	/**
	 * 表单项列表
	 */
	protected List<WfFormField> wfFormFields;

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getFormKey() {
		return formKey;
	}

	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

	public List<WfFormField> getWfFormFields() {
		return wfFormFields;
	}

	public void setWfFormField(List<WfFormField> wfFormFields) {
		this.wfFormFields = wfFormFields;
	}

}
