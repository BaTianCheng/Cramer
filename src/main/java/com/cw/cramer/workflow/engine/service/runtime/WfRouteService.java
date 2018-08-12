package com.cw.cramer.workflow.engine.service.runtime;

import java.util.List;
import java.util.UUID;

import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 路由服务（提供扩展的路由）
 * @author wicks
 */
@Service(value = "wfRouteService")
public class WfRouteService {
	
	/**
	 * 提供单独接口：
	 * 转办：办理人委托他人进行处理，自己是持有人，他人是处理人
	 * 会签：指定若干个人进行确认
	 * 
	 * 提供内置定义：
	 * 通知：根据处理结果，即operate和result进行结果通知
	 * 
	 */
	
	@Autowired
	TaskService taskService;
	
	/**
	 * 转办(Activiti6.0已新增该功能，不再额外实现)
	 * @param taskId
	 * @param assignee
	 */
	public void delegateTask(String taskId, String assignee) {
		taskService.delegateTask(taskId, assignee);
	}
	
	/**
	 * 会签(实际使用需要根据业务调整)
	 * @param taskId
	 * @param assignees
	 * @throws Exception
	 */
	public void countersign(String taskId, List<String> assignees) throws Exception {
		Task currentTask = taskService
				.createTaskQuery()
				.taskId(taskId)
				.singleResult();

		// 创建会签任务
		for (String assignee : assignees) {
			TaskEntity task = (TaskEntity) taskService.newTask(UUID.randomUUID().toString());
			task.setAssignee(assignee);
			task.setName(currentTask.getName() + "-会签");
			task.setProcessDefinitionId(currentTask.getProcessDefinitionId());
			task.setProcessInstanceId(currentTask.getProcessInstanceId());
			task.setParentTaskId(taskId);
			task.setDescription("countersign");
			taskService.saveTask(task);
		}
	}
	
	/**
	 * 通知
	 * @param taskId
	 * @param assignee
	 */
	public void notify(String taskId, String to) {
		Task currentTask = taskService
				.createTaskQuery()
				.taskId(taskId)
				.singleResult();
		
		String msg = currentTask.getAssignee();
		System.out.println(msg);
		
		//待调用通知接口
	}
	
	

}
