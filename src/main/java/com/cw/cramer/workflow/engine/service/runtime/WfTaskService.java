package com.cw.cramer.workflow.engine.service.runtime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.workflow.engine.entity.WfTask;
import com.cw.cramer.workflow.engine.util.WfConvertUtils;
import com.cw.cramer.common.util.DateTimeUtils;
import com.cw.cramer.workflow.engine.entity.WfQueryTask;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;

/**
 * 工作流任务实例服务类
 * @author wicks
 */
@Service(value = "wfTaskService")
public class WfTaskService {
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	WfInstanceService wfInstanceService;
	
	/**
	 * 获取待办任务列表(已分配+待签收)
	 * @param assignee
	 * @return
	 */
	public List<WfTask> getUndoTasks(String assignee) {
		List<WfTask> undoTasks = new ArrayList<>();
		
		List<Task> tasks = taskService
				.createTaskQuery()
				.taskCandidateOrAssigned(assignee)
				.orderByTaskCreateTime()
				.desc()
				.list();
		undoTasks = WfConvertUtils.convertWfTasks(tasks);
		
		//注入实例对象
		for(WfTask task : undoTasks) {
			task.setWfInstance(wfInstanceService.getInstance(task.getInstanceId()));
		}
		
		return undoTasks;
	}
	
	/**
	 * 获取待办任务列表(已分配+待签收)
	 * @param queryCondition
	 * @return
	 */
	public PageInfo<WfTask> getUndoTasks(WfQueryTask queryCondition) {
		List<WfTask> undoTasks = new ArrayList<>();
		PageInfo<WfTask> pages = new PageInfo<>();
		List<Task> tasks = new ArrayList<>();
		
		TaskQuery query = taskService.createTaskQuery();
		
		if (!Strings.isNullOrEmpty(queryCondition.getCreator())) {
			query = query.processVariableValueEquals("applyUserId", queryCondition.getCreator());
		}

		if (!Strings.isNullOrEmpty(queryCondition.getAssignee())) {
			query = query.taskAssignee(queryCondition.getAssignee());
		}

		if (!Strings.isNullOrEmpty(queryCondition.getStartTime())) {
			Date date = DateTimeUtils.parseDate(queryCondition.getStartTime());
			query = query.taskCreatedAfter(date);
		}

		if (!Strings.isNullOrEmpty(queryCondition.getEndTime())) {
			Date date = DateTimeUtils.parseDate(queryCondition.getEndTime());
			query = query.taskCreatedBefore(date);
		}

		if (!Strings.isNullOrEmpty(queryCondition.getTemplateKey())) {
			query = query.processDefinitionKey(queryCondition.getTemplateKey());
		}

		// 排序
		query = query.orderByTaskCreateTime().desc();
		
		// 分页处理
		if (queryCondition.getPageSize() > 0) {
			tasks = query.listPage(queryCondition.getPageNum(), queryCondition.getPageSize());
			pages.setPageNum(queryCondition.getPageNum());
			pages.setPageSize(queryCondition.getPageSize());
			pages.setSize(query.list().size());
		} else {
			tasks = query.list();
		}
		
		undoTasks = WfConvertUtils.convertWfTasks(tasks);
		pages.setList(undoTasks);
		
		return pages;
	}
	
	/**
	 * 获得任务信息
	 * @param taskId
	 * @return
	 */
	public WfTask geTask(String taskId) {
		WfTask wfTask = new WfTask();
		
		Task task = taskService
				.createTaskQuery()
				.taskId(taskId)
				.singleResult();
		
		wfTask = WfConvertUtils.convertWfTask(task);
		return wfTask;
	}

}
