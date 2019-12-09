package com.cw.cramer.workflow.engine.service.define;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.workflow.engine.entity.WfTemplate;
import com.cw.cramer.workflow.engine.util.WfConvertUtils;

/**
 * 工作流模板服务类
 * @author wicks
 */
@Service(value = "templateService")
public class WfTemplateService {
	
	@Autowired
	RepositoryService repositoryService;
	
	/**
	 * 停用工作流模板
	 * @param templateKey
	 */
	public void suspendTemplate(String templateKey) {
		repositoryService.suspendProcessDefinitionByKey(templateKey);
	}
	
	/**
	 * 启用工作流模板
	 * @param templateKey
	 */
	public void activateTemplate(String templateKey) {
		repositoryService.activateProcessDefinitionByKey(templateKey);
	}
	
	/**
	 * 获取工作流模板
	 * @param templateKey
	 * @return
	 */
	public WfTemplate getTemplate(String templateKey) {
		WfTemplate wfTemplate = new WfTemplate();
		
		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionKey(templateKey)
				.latestVersion()
				.singleResult();
		
		wfTemplate = WfConvertUtils.convertWfTemplate(processDefinition);
		return wfTemplate;
	}
	
	/**
	 * 获取所有工作流模板列表 
	 * @return
	 */
	public List<WfTemplate> getTemplates() {
		List<WfTemplate> wfTemplates = new ArrayList<>();
		
		List<ProcessDefinition> processDefinitions = repositoryService
				.createProcessDefinitionQuery()
				.latestVersion()
				.orderByProcessDefinitionVersion()
				.asc()
				.list();
		
		wfTemplates = WfConvertUtils.convertWfTemplates(processDefinitions);
		return wfTemplates;
	}
	
	/**
	 * 获取流程模板图像流
	 * @param templateKey
	 * @return
	 */
	public InputStream getTemplateImage(String templateKey) {
		WfTemplate wfTemplate = getTemplate(templateKey);
		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionId(wfTemplate.getDefinionId())
				.singleResult();
		String imageName = processDefinition.getDiagramResourceName();
		InputStream imageStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), imageName);
		
		return imageStream;
	}

}
