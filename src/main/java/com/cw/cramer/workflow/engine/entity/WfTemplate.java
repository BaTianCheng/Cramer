package com.cw.cramer.workflow.engine.entity;

import java.io.Serializable;

/**
 * Template
 * @author wicks
 */
public class WfTemplate implements Serializable{

	private static final long serialVersionUID = 498384431405324684L;

	/**
	 * 模板编号
	 */
	protected String templateKey;
	
	/**
	 * 模板名称
	 */
	protected String templateName;
	
	/**
	 * 流程定义编号
	 */
	protected String definionId;
	
	/**
	 * 模板描述
	 */
	protected String description;
	
	/**
	 * 模板内容文件路径
	 */
	protected String templatePath;
	
	/**
	 * 是否可用
	 */
	protected boolean enabled;
	

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
	
	public String getDefinionId() {
		return definionId;
	}

	public void setDefinionId(String definionId) {
		this.definionId = definionId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
