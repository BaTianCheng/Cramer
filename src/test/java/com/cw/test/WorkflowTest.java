package com.cw.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.cw.cramer.common.util.SpringBeanUtils;
import com.cw.cramer.workflow.auth.CustomUserEntityManager;

/**
 * 工作流测试类
 * @author wicks
 */
public class WorkflowTest {
	
	public static void test() {
		
	}

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		ApplicationContext act = new ClassPathXmlApplicationContext(new String[]{"config/spring/spring-context.xml","config/spring/spring-context-shiro.xml","config/spring/spring-mvc.xml","config/spring/spring-context-activiti.xml"});
		
		// Spring注入获取
		RepositoryService repositoryService = (RepositoryService)act.getBean("repositoryService");
		RuntimeService runtimeService = (RuntimeService)act.getBean("runtimeService");
		TaskService taskService = (TaskService)act.getBean("taskService");
		HistoryService historyService = (HistoryService)act.getBean("historyService");
		CustomUserEntityManager customUserEntityManager = (CustomUserEntityManager) SpringBeanUtils.getBeanByName("customUserEntityManager");
		

		// 部署流程文件
		repositoryService.createDeployment().addClasspathResource("config/activiti/testflow.bpmn").deploy();

		// 运行流程(申请(金额2000)--经理审批(同意)--结束)
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("ApproveTest","A1002");
		System.out.println("【启动流程----"+pi.getId()+"||"+pi.getBusinessKey()+"】");

		// 员工A1提出报销申请
		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
		task.setAssignee("35");
		taskService.setAssignee(task.getId(), "35");
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> form = new HashMap<>();
		form.put("money", 2000);
		form.put("text", "交通费用2000元");
		map.put("form", form);
		taskService.complete(task.getId(), map);
		System.out.println("处理任务"+task.getProcessInstanceId()+"：【" + task.getName() + "】,处理人：" + customUserEntityManager.findById(task.getAssignee()).getLastName() + "，提交表单："+JSON.toJSONString(map));
		
		// 经理B1查看哪些待处理的流程
		List<Task> tasks = taskService.createTaskQuery().taskCandidateUser("36").processDefinitionKey("ApproveTest").list();
		System.out.println(customUserEntityManager.findById("36").getLastName() + "查看待处理任务列表，共"+tasks.size()+"条");
		for(Task approveTask : tasks) {
			approveTask.setAssignee("36");
			taskService.setAssignee(approveTask.getId(), "36");
			form.put("approve1", true);
			map.put("form", form);
			taskService.complete(approveTask.getId(), map);
			System.out.println("处理任务"+task.getProcessInstanceId()+"：【" + approveTask.getName() + "】,处理人：" + customUserEntityManager.findById(approveTask.getAssignee()).getLastName() + "，提交表单："+JSON.toJSONString(map));
			task = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
			System.out.println("流程结束");
		}
		System.out.println("=================================================================");
		
		// 运行流程(申请(金额8000)--总监审批(驳回)--修改申请--提交申请--总监审批(同意)--结束)
		pi = runtimeService.startProcessInstanceByKey("Approve");
		System.out.println("【启动流程----"+pi.getId()+"】");

		// 员工A1提出报销申请
		task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
		task.setAssignee("35");
		taskService.setAssignee(task.getId(), "35");
		map = new HashMap<>();
		form = new HashMap<>();
		form.put("money", 8000);
		form.put("text", "招待费8000元");
		map.put("form", form);
		taskService.complete(task.getId(), map);
		System.out.println("处理任务"+task.getProcessInstanceId()+"：【" + task.getName() + "】,处理人：" + customUserEntityManager.findById(task.getAssignee()).getLastName() + "，提交表单："+JSON.toJSONString(map));
		
		// 总监C1进行审批（驳回）
		task = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
		task.setAssignee("37");
		taskService.setAssignee(task.getId(), "37");
		form.put("approve2", false);
		map.put("form", form);
		taskService.complete(task.getId(), map);
		System.out.println("处理任务"+task.getProcessInstanceId()+"：【" + task.getName() + "】,处理人：" + customUserEntityManager.findById(task.getAssignee()).getLastName() + "，提交表单："+JSON.toJSONString(map));
		
		// 员工A1修改并提交
		task = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
		task.setAssignee("35");
		taskService.setAssignee(task.getId(), "35");
		form.put("text", "住宿费8000元");
		form.put("confirm", true);
		taskService.complete(task.getId(), map);
		System.out.println("处理任务"+task.getProcessInstanceId()+"：【" + task.getName() + "】,处理人：" + customUserEntityManager.findById(task.getAssignee()).getLastName() + "，提交表单："+JSON.toJSONString(map));
		task = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
		task.setAssignee("35");
		taskService.complete(task.getId(), map);
		System.out.println("处理任务"+task.getProcessInstanceId()+"：【" + task.getName() + "】,处理人：" + customUserEntityManager.findById(task.getAssignee()).getLastName() + "，提交表单："+JSON.toJSONString(map));
		
		// 总监C1进行审批（通过）
		task = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
		task.setAssignee("37");
		taskService.setAssignee(task.getId(), "37");
		form.put("approve2", true);
		map.put("form", form);
		taskService.complete(task.getId(), map);
		System.out.println("处理任务"+task.getProcessInstanceId()+"：【" + task.getName() + "】,处理人：" + customUserEntityManager.findById(task.getAssignee()).getLastName() + "，提交表单："+JSON.toJSONString(map));
		
		task = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
		System.out.println("流程结束");
		System.out.println("=================================================================");
		
		// 历史记录展示
		historyService.createHistoricProcessInstanceQuery().finished().list();
		
		

	}

}
