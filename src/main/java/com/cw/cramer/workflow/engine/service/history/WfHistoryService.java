package com.cw.cramer.workflow.engine.service.history;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricFormProperty;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.workflow.engine.entity.WfFormField;
import com.cw.cramer.workflow.engine.entity.WfInstance;
import com.cw.cramer.workflow.engine.entity.WfTask;
import com.cw.cramer.workflow.engine.util.WfConvertUtils;
import com.cw.cramer.workflow.engine.entity.WfQueryInstance;
import com.cw.cramer.workflow.engine.entity.WfQueryTask;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;

import cn.hutool.core.date.DateUtil;

/**
 * 工作流历史服务类
 * @author wicks
 */
@Service(value = "wfHistoryService")
public class WfHistoryService {
	
	public static Logger logger = LoggerFactory.getLogger(WfHistoryService.class);
	
	@Autowired
	HistoryService historyService;
	
	/**
	 * 获取已处理任务列表
	 * @param assignee
	 * @return
	 */
	public List<WfTask> getDoneTasks(String assignee) {
		List<WfTask> wfTasks = new ArrayList<>();
		
		List<HistoricTaskInstance> historicTaskInstances = historyService
				.createHistoricTaskInstanceQuery()
				.taskAssignee(assignee)
				.finished()
				.orderByHistoricTaskInstanceEndTime()
				.desc()
				.list();
		wfTasks = WfConvertUtils.convertWfHiscTasks(historicTaskInstances);
		
		for(WfTask task : wfTasks) {
			WfInstance wfInstance = WfConvertUtils.convertWfInstances(
					historyService
					.createHistoricProcessInstanceQuery()
					.processInstanceId(task.getInstanceId())
					.singleResult()
				);
			task.setWfInstance(wfInstance);
		}
		
		return wfTasks;
	}
	
	/**
	 * 获取已处理任务列表
	 * @param queryCondition
	 * @return
	 */
	public List<WfTask> getDoneTasks(WfQueryTask queryCondition) {
		List<WfTask> wfTasks = new ArrayList<>();
		PageInfo<WfTask> pages = new PageInfo<>();
		List<HistoricTaskInstance> historicTaskInstances = new ArrayList<>();
		
		HistoricTaskInstanceQuery query = historyService.createHistoricTaskInstanceQuery();

		if (!Strings.isNullOrEmpty(queryCondition.getCreator())) {
			query = query.processVariableValueEquals("applyUserId", queryCondition.getCreator());
		}

		if (!Strings.isNullOrEmpty(queryCondition.getAssignee())) {
			query = query.taskAssignee(queryCondition.getAssignee());
		}

		if (!Strings.isNullOrEmpty(queryCondition.getStartTime())) {
			Date date = DateUtil.parse(queryCondition.getStartTime());
			query = query.taskCompletedAfter(date);
		}

		if (!Strings.isNullOrEmpty(queryCondition.getEndTime())) {
			Date date = DateUtil.parse(queryCondition.getEndTime());
			query = query.taskCompletedBefore(date);
		}

		if (!Strings.isNullOrEmpty(queryCondition.getTemplateKey())) {
			query = query.processDefinitionKey(queryCondition.getTemplateKey());
		}

		// 排序
		query = query.finished();
		query = query.orderByHistoricTaskInstanceEndTime().desc();

		// 分页处理
		if (queryCondition.getPageSize() > 0) {
			historicTaskInstances = query.listPage(queryCondition.getPageNum(), queryCondition.getPageSize());
			pages.setPageNum(queryCondition.getPageNum());
			pages.setPageSize(queryCondition.getPageSize());
			pages.setSize(query.list().size());
		} else {
			historicTaskInstances = query.list();
		}
				
		wfTasks = WfConvertUtils.convertWfHiscTasks(historicTaskInstances);
		pages.setList(wfTasks);
		
		return wfTasks;
	}
	
	/**
	 * 获取我的已完成实例列表
	 * @param creator
	 * @return
	 */
	public List<WfInstance> getFinishedInstancesByCreator(String creator) {
		List<WfInstance> wfInstances = new ArrayList<>();
		
		List<HistoricProcessInstance> historicProcessInstance = historyService
				.createHistoricProcessInstanceQuery()
				.startedBy(creator)
				.finished()
				.orderByProcessInstanceEndTime()
				.desc()
				.list();
		
		wfInstances = WfConvertUtils.convertWfHiscInstances(historicProcessInstance);
		return wfInstances;
	}
	
	/**
	 * 获取我的已完成实例列表
	 * @param queryCondition
	 * @return
	 */
	public PageInfo<WfInstance> getFinishedInstancesByCreator(WfQueryInstance queryCondition) {
		List<WfInstance> WfInstances = new ArrayList<>();
		PageInfo<WfInstance> pages = new PageInfo<>();
		List<HistoricProcessInstance> historicProcessInstances = new ArrayList<>();
		
		HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();
		
		if(!Strings.isNullOrEmpty(queryCondition.getCreator())) {
			query = query.startedBy(queryCondition.getCreator());
		}
		
		if(!Strings.isNullOrEmpty(queryCondition.getStartTime())) {
			Date date = DateUtil.parse(queryCondition.getStartTime());
			query = query.startedAfter(date);
		}
		
		if(!Strings.isNullOrEmpty(queryCondition.getEndTime())) {
			Date date = DateUtil.parse(queryCondition.getEndTime());
			query = query.startedBefore(date);
		}
		
		if(!Strings.isNullOrEmpty(queryCondition.getTemplateKey())) {
			query = query.processDefinitionKey(queryCondition.getTemplateKey());
		}
		
		// 排序
		query = query.finished();
		query = query.orderByProcessInstanceEndTime().desc();
		
		// 分页处理
		if(queryCondition.getPageSize() > 0){
			historicProcessInstances = query.listPage(queryCondition.getPageNum(), queryCondition.getPageSize());
			pages.setPageNum(queryCondition.getPageNum());
			pages.setPageSize(queryCondition.getPageSize());
			pages.setSize(query.list().size());
		} else {
			historicProcessInstances = query.list();
		}
		
		WfInstances = WfConvertUtils.convertWfHiscInstances(historicProcessInstances);
		pages.setList(WfInstances);
		
		return pages;
	}
	
	/**
	 * 获取任务历史实例列表
	 * @param instanceId
	 * @return
	 */
	public List<WfTask> getWfHistoricTasks(String instanceId) {
		List<WfTask> wfTaskInstances = new ArrayList<>();
		
		List<HistoricActivityInstance> historicActivityInstances = historyService
				.createHistoricActivityInstanceQuery()
				.processInstanceId(instanceId)
				.orderByHistoricActivityInstanceEndTime()
				.asc()
				.list();
		
		if(historicActivityInstances != null) {
			for(HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
				if("startEvent".equals(historicActivityInstance.getActivityType())
						|| "endEvent".equals(historicActivityInstance.getActivityType())
						|| "userTask".equals(historicActivityInstance.getActivityType())) {
					WfTask wfTaskInstance = new WfTask();
					wfTaskInstance.setAssigne(historicActivityInstance.getAssignee());
					wfTaskInstance.setEndTime(DateUtil.formatDateTime(historicActivityInstance.getEndTime()));
					wfTaskInstance.setInstanceId(instanceId);
					wfTaskInstance.setTaskKey(historicActivityInstance.getActivityId());
					wfTaskInstance.setTaskId(historicActivityInstance.getTaskId());
					wfTaskInstance.setTaskName(historicActivityInstance.getActivityName());
					wfTaskInstance.setFormFields(getWfFormFieldByHistoricTask(historicActivityInstance.getId()));
					
					// 添加描述
					StringBuilder desc = new StringBuilder();
					switch(historicActivityInstance.getActivityType()) {
						case "startEvent" : desc.append("工作流开始，["+wfTaskInstance.getTaskName()+"]；");
							break;
						case "endEvent" : desc.append("工作流结束，["+wfTaskInstance.getTaskName()+"]；");
							break;
						default: desc.append("处理了任务["+wfTaskInstance.getTaskName()+"]；");
					}
								
					if(wfTaskInstance.getFormFields().size() > 0){
						desc.append("内容：");
						for(WfFormField wfFormField : wfTaskInstance.getFormFields()) {
							desc.append(wfFormField.getId()+"："+wfFormField.getValue()+"；");
						}
					}
					
					wfTaskInstance.setDescription(desc.toString());
					wfTaskInstances.add(wfTaskInstance);
				}
				
			}
		}
		
		return wfTaskInstances;
	}
	
	/**
	 * 获取历史任务中的表单提交值
	 * @param activityId
	 * @return
	 */
	private List<WfFormField> getWfFormFieldByHistoricTask(String activityId) {
		List<WfFormField> wfFormFields = new ArrayList<>();

		List<HistoricDetail> historicDetails = historyService
				.createHistoricDetailQuery()
				.activityInstanceId(activityId)
				.list();

		for (HistoricDetail historicDetail : historicDetails) {
			if (historicDetail instanceof HistoricFormProperty) {
				// 表单中的字段
				HistoricFormProperty field = (HistoricFormProperty) historicDetail;
				WfFormField wfFormField = new WfFormField();
				wfFormField.setId(field.getPropertyId());
				wfFormField.setValue(field.getPropertyValue());
				wfFormFields.add(wfFormField);
			}
		}
		
		return wfFormFields;
	}

}
