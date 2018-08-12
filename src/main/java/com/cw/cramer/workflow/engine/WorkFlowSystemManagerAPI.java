package com.cw.cramer.workflow.engine;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.workflow.engine.entity.WfTemplate;
import com.cw.cramer.workflow.engine.service.define.WfDeploymentService;
import com.cw.cramer.workflow.engine.service.define.WfTemplateService;

/**
 * 工作流管理API
 * @author wicks
 */
@Service(value = "workFlowSystemManagerAPI")
public class WorkFlowSystemManagerAPI {
	
	@Autowired
	WfDeploymentService deploymentService;
	
	@Autowired
	WfTemplateService wfTemplateService;
	
	/**
	 * 对所有工作流定义模板进行部署
	 */
	public void deploy() {
		deploymentService.deploy();
	}
	
	/**
	 * 部署工作流模板
	 * @param templateKey
	 * @param inputStream
	 */
	public void deploy(String fileName) {
		deploymentService.deploy(fileName);
	}
	
	/**
	 * 部署工作流模板
	 * @param templateKey
	 * @param inputStream
	 */
	public void deploy(String templateKey, InputStream inputStream) {
		deploymentService.deploy(templateKey, inputStream);
	}
	
	/**
	 * 停用工作流模板
	 * @param templateKey
	 */
	public void suspendTemplate(String templateKey) {
		wfTemplateService.suspendTemplate(templateKey);
	}
	
	/**
	 * 启用工作流模板
	 * @param templateKey
	 */
	public void activateTemplate(String templateKey) {
		wfTemplateService.activateTemplate(templateKey);
	}
	
	/**
	 * 获取工作流模板
	 * @param templateKey
	 * @return
	 */
	public WfTemplate getTemplate(String templateKey) {
		return wfTemplateService.getTemplate(templateKey);
	}
	
	/**
	 * 获取所有工作流模板列表
	 * @return
	 */
	public List<WfTemplate> getTemplates() {
		return wfTemplateService.getTemplates();
	}
	
	/**
	 * 获取流程模板图像流
	 * @param templateKey
	 * @return
	 */
	public InputStream getTemplateImage(String templateKey) {
		return wfTemplateService.getTemplateImage(templateKey);
	}

}
