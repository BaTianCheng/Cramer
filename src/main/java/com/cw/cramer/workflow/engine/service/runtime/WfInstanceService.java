package com.cw.cramer.workflow.engine.service.runtime;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.workflow.engine.entity.WfInstance;
import com.cw.cramer.workflow.engine.entity.WfTask;
import com.cw.cramer.workflow.engine.util.WfConvertUtils;
import com.cw.cramer.common.util.DateTimeUtils;
import com.cw.cramer.workflow.engine.entity.WfQueryInstance;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;


/**
 * 工作流实例服务类
 * @author wicks
 */
@Service(value="wfInstanceService")
public class WfInstanceService {
	
	public static Logger logger = LoggerFactory.getLogger(WfInstanceService.class);
	
	@Autowired
	RepositoryService repositoryService;
	
	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	FormService formService;
	
	@Autowired
	IdentityService identityService;
	
	/**
	 * 开启工作流实例
	 * @param templateKey 工作流模板Key
	 * @param assignee 发起人
	 * @param form 表单
	 * @return 工作流实例编号
	 */
	public String start(String templateKey, String assignee, Map<String, String> form) {
		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionKey(templateKey)
				.latestVersion()
				.singleResult();
		identityService.setAuthenticatedUserId(assignee);
		ProcessInstance instance = formService.submitStartFormData(processDefinition.getId(), form);
		logger.info("【工作流创建】"+assignee+"创建了工作流-"+processDefinition.getName()+"，实例编号："+instance.getId());
		return instance.getId();
	}
	
	/**
	 * 开启工作流实例
	 * @param templateKey 工作流模板Key
	 * @param assignee 发起人
	 * @param form 表单
	 * @param bussinessKey 业务编号
	 * @return 工作流实例编号
	 */
	public String start(String templateKey, String assignee, Map<String, String> form, String bussinessKey) {
		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionKey(templateKey)
				.latestVersion()
				.singleResult();
		identityService.setAuthenticatedUserId(assignee);
		ProcessInstance instance = formService.submitStartFormData(processDefinition.getId(), bussinessKey, form);
		logger.info("【工作流创建】"+assignee+"创建了工作流-"+processDefinition.getName()+"，实例编号："+instance.getId()+"，业务编号："+bussinessKey);
		return instance.getId();
	}
	
	/**
	 * 完成工作流节点
	 * @param taskId 工作流任务编号
	 * @param assignee 该实例的实际处理人
	 * @param form 输入的表单信息
	 */
	public void complete(String taskId, String assignee, Map<String, String> form) {
		Task task = taskService
				.createTaskQuery()
				.taskId(taskId)
				.singleResult();
		taskService.claim(task.getId(), assignee);
		
		// 非空校验
		if(form == null) {
			form = new HashMap<>();
		}
		
		formService.submitTaskFormData(task.getId(), form);
		logger.info("【工作流处理】"+assignee+"完成了工作流任务-"+task.getId()+"-"+task.getName()+"的处理");
	}
	
	/**
	 * 完成工作流节点
	 * @param taskId 工作流任务编号
	 * @param assignee 该实例的实际处理人
	 * @param form 输入的表单信息
	 */
	public void complete(String taskId, String assignee) {
		Task task = taskService
				.createTaskQuery()
				.taskId(taskId)
				.singleResult();
		taskService.claim(task.getId(), assignee);
		taskService.complete(task.getId());
		logger.info("【工作流处理】"+assignee+"完成了工作流任务-"+task.getId()+"-"+task.getName()+"的处理");
	}
	
	/**
	 * 获取当前实例的任务
	 * @param instanceId
	 * @return
	 */
	public List<WfTask> getCurrentTask(String instanceId) {
		List<WfTask> wfTasks = new ArrayList<>();
		
		List<Task> tasks = taskService
				.createTaskQuery()
				.processInstanceId(instanceId)
				.list();
		
		wfTasks = WfConvertUtils.convertWfTasks(tasks);
		return wfTasks;
	}
	
	/**
	 * 获取我的处理中的实例列表
	 * @param creator
	 * @return
	 */
	public List<WfInstance> getUndoInstancesByCreator(String creator) {
		List<WfInstance> wfInstances =  new ArrayList<>();
		
		List<ProcessInstance> processInstances = runtimeService
				.createProcessInstanceQuery()
				.startedBy(creator)
				.orderByProcessInstanceId()
				.asc()
				.list();
		
		wfInstances = WfConvertUtils.convertWfInstances(processInstances);
		return wfInstances;
	}

	/**
	 * 获取我的处理中的实例列表
	 * @param queryCondition
	 * @return
	 */
	public PageInfo<WfInstance> getUndoInstancesByCreator(WfQueryInstance queryCondition) {
		List<WfInstance> wfInstances =  new ArrayList<>();
		PageInfo<WfInstance> pages = new PageInfo<>();
		List<ProcessInstance> processInstances = new ArrayList<>();
		
		ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();
		
		if(!Strings.isNullOrEmpty(queryCondition.getCreator())) {
			query = query.startedBy(queryCondition.getCreator());
		}
		
		if(!Strings.isNullOrEmpty(queryCondition.getStartTime())) {
			Date date = DateTimeUtils.parseDate(queryCondition.getStartTime());
			query = query.startedAfter(date);
		}
		
		if(!Strings.isNullOrEmpty(queryCondition.getEndTime())) {
			Date date = DateTimeUtils.parseDate(queryCondition.getEndTime());
			query = query.startedBefore(date);
		}
		
		if(!Strings.isNullOrEmpty(queryCondition.getTemplateKey())) {
			query = query.processDefinitionKey(queryCondition.getTemplateKey());
		}
		
		// 排序
		query = query.orderByProcessInstanceId().asc();
		
		// 分页处理
		if(queryCondition.getPageSize() > 0){
			processInstances = query.listPage(queryCondition.getPageNum(), queryCondition.getPageSize());
			pages.setPageNum(queryCondition.getPageNum());
			pages.setPageSize(queryCondition.getPageSize());
			pages.setSize(query.list().size());
		} else {
			processInstances = query.list();
		}
		
		wfInstances = WfConvertUtils.convertWfInstances(processInstances);
		pages.setList(wfInstances);
		
		return pages;
	}
	
	/**
	 * 获得工作流实例
	 * @param instanceId
	 * @return
	 */
	public WfInstance getInstance(String instanceId) {
		WfInstance wfInstance = new WfInstance();
		
		ProcessInstance processInstance = runtimeService
				.createProcessInstanceQuery()
				.processInstanceId(instanceId)
				.singleResult();
		
		wfInstance = WfConvertUtils.convertWfInstance(processInstance);
		return wfInstance;
	}
	
	
}
