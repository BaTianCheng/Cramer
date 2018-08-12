package com.cw.cramer.workflow.engine;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.workflow.engine.entity.WfForm;
import com.cw.cramer.workflow.engine.entity.WfInstance;
import com.cw.cramer.workflow.engine.entity.WfTask;
import com.cw.cramer.workflow.engine.entity.WfTemplate;
import com.cw.cramer.workflow.engine.service.authorize.WfAuthorizeService;
import com.cw.cramer.workflow.engine.service.define.WfTemplateService;
import com.cw.cramer.workflow.engine.service.history.WfHistoryService;
import com.cw.cramer.workflow.engine.service.runtime.WfInstanceFormService;
import com.cw.cramer.workflow.engine.service.runtime.WfInstanceService;
import com.cw.cramer.workflow.engine.service.runtime.WfRouteService;
import com.cw.cramer.workflow.engine.service.runtime.WfTaskService;

/**
 * 工作流统一API
 * @author wicks
 */
@Service(value="workFlowAPI")
public class WorkFlowAPI {
	
	@Autowired
	WfInstanceService instanceService;
	
	@Autowired
	WfInstanceFormService instanceFormService;
	
	@Autowired
	WfTaskService taskService;
	
	@Autowired
	WfHistoryService historyService;
	
	@Autowired
	WfAuthorizeService authorizeService;
	
	@Autowired
	WfTemplateService templateService;
	
	@Autowired
	WfRouteService wfRouteService;
	
	
	/**
	 * 开启一个工作流实例
	 * @param templateKey 模板Key
	 * @param assignee 发起人
	 * @param form 表单信息
	 * @return 工作流实例编号
	 */
	public String start(String templateKey, String assignee, Map<String, String> form) {
		return instanceService.start(templateKey, assignee, form);
	}
	
	/**
	 * 开启工作流实例
	 * @param templateKey 模板Key
	 * @param assignee 发起人
	 * @param form 表单信息
	 * @param bussinessKey 业务编号
	 * @return 工作流实例编号
	 */
	public String start(String templateKey, String assignee, Map<String, String> form, String bussinessKey) {
		return instanceService.start(templateKey, assignee, form);
	}
	
	/**
	 * 完成工作流节点
	 * @param taskId 工作流任务例编号
	 * @param assignee 该实例的实际处理人
	 * @param form 输入的表单信息
	 */
	public void complete(String taskId, String assignee, Map<String, String> form) {
		instanceService.complete(taskId, assignee, form);
	}
	
	/**
	 * 完成工作流节点
	 * @param taskId 工作流任务编号
	 * @param assignee 该实例的实际处理人
	 * @param form 输入的表单信息
	 */
	public void complete(String taskId, String assignee) {
		instanceService.complete(taskId, assignee);
	}
	
	/**
	 * 获取用户可发起的流程模板
	 * @param creator
	 * @return
	 */
	public List<WfTemplate> getTemplates(String creator) {
		return authorizeService.getTemplates(creator);
	}
	
	/**
	 * 获取待办任务列表(已分配+待签收)
	 * @param assignee
	 * @return
	 */
	public List<WfTask> getUndoTasks(String assignee) {
		return taskService.getUndoTasks(assignee);
	}
	
	/**
	 * 获取已处理任务列表
	 * @param assignee
	 * @return
	 */
	public List<WfTask> getDoneTasks(String assignee) {
		return historyService.getDoneTasks(assignee);
	}
	
	/**
	 * 获取我的已归档实例列表
	 * @param assignee
	 * @return
	 */
	public List<WfInstance> getFinishedInstancesByCreator(String creator) {
		return historyService.getFinishedInstancesByCreator(creator);
	}
	
	/**
	 * 获取我的处理中实例列表
	 * @param assignee
	 * @return
	 */
	public List<WfInstance> getUndoInstancesByCreator(String creator) {
		return instanceService.getUndoInstancesByCreator(creator);
	}
	
	/**
	 * 获取所有工作流模板列表
	 * @return
	 */
	public List<WfTemplate> getTemplates() {
		return templateService.getTemplates();
	}
	
	/**
	 * 获取工作流模板
	 * @return
	 */
	public WfTemplate geTemplate(String templateKey) {
		return templateService.getTemplate(templateKey);
	}
	
	/**
	 * 获取工作流模板初始表单定义
	 * @param templateKey
	 * @return 工作流表单
	 */
	public WfForm getTemplateStartForm(String templateKey) {
		return instanceFormService.getTemplateStartForm(templateKey);
	}
	
	/**
	 * 获取工作流实例表单数据
	 * @param taskId 任务编号
	 * @return 工作流表单
	 */
	public WfForm getTaskForm(String taskId) {
		return instanceFormService.getTaskForm(taskId);
	}
	
	/**
	 * 获取工作流实例当前任务列表
	 * @param instanceId 实例编号
	 * @return 任务列表
	 */
	public List<WfTask> getCurrentTask(String instanceId) {
		return instanceService.getCurrentTask(instanceId);
	}
	
	/**
	 * 获取工作流实例历史任务列表
	 * @param instanceId 实例编号
	 * @return 任务列表
	 */
	public List<WfTask> getWfHistoricTasks(String instanceId) {
		return historyService.getWfHistoricTasks(instanceId);
	}
	
	/**
	 * 获得工作流实例
	 * @param instanceId
	 * @return
	 */
	public WfInstance getInstance(String instanceId) {
		return instanceService.getInstance(instanceId);
	}
	
	/**
	 * 获得工作流任务实例
	 * @param taskId
	 * @return
	 */
	public WfTask geTask(String taskId) {
		return taskService.geTask(taskId);
	}

	/**
	 * 转办
	 * @param taskId
	 * @param assignee
	 */
	public void delegateTask(String taskId, String assignee) {
		wfRouteService.delegateTask(taskId, assignee);
	}
	
}
