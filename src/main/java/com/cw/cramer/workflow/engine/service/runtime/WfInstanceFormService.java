package com.cw.cramer.workflow.engine.service.runtime;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormData;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.workflow.engine.entity.WfForm;
import com.cw.cramer.workflow.engine.util.WfConvertUtils;

/**
 * 工作流实例表单服务类
 * @author wicks
 */
@Service(value = "wfInstanceFormService")
public class WfInstanceFormService {
	
	@Autowired
	RepositoryService repositoryService;
	
	@Autowired
	FormService formService;
	
	@Autowired
	TaskService taskService;
	
	/**
	 * 获取工作流模板初始表单定义
	 * @param templateKey
	 * @return 工作流表单
	 */
	public WfForm getTemplateStartForm(String templateKey) {
		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionKey(templateKey)
				.latestVersion()
				.singleResult();
		StartFormData startFormData = formService.getStartFormData(processDefinition.getId());
		WfForm wfForm = WfConvertUtils.covertWfForm(startFormData);
		return wfForm;
	}
	
	/**
	 * 获取工作流实例表单数据
	 * @param taskId 任务编号
	 * @return 工作流表单
	 */
	public WfForm getTaskForm(String taskId) {
		Task task = taskService
				.createTaskQuery()
				.taskId(taskId)
				.singleResult();
		FormData formData = formService.getTaskFormData(task.getId());
		WfForm wfForm = WfConvertUtils.covertWfForm(formData);
		return wfForm;
	}

}
