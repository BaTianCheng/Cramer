package com.cw.cramer.workflow.engine.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.form.FormData;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricFormProperty;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.cw.cramer.common.util.DateTimeUtils;
import com.cw.cramer.workflow.engine.entity.WfForm;
import com.cw.cramer.workflow.engine.entity.WfFormField;
import com.cw.cramer.workflow.engine.entity.WfInstance;
import com.cw.cramer.workflow.engine.entity.WfTask;
import com.cw.cramer.workflow.engine.entity.WfTemplate;

/**
 * 描 述: 转换工具类
 * @author wicks
 */
@Service
@Lazy(value=false)
public class WfConvertUtils {
	
	@Autowired
	FormService autoFormService;
	
	@Autowired
	HistoryService autoHistoryService;
	
	private static FormService formService;
	
	private static HistoryService historyService;
	
	/**
	 * 初始化，将注入bean传值给静态变量
	 */
	@PostConstruct
	public void init() {
		formService = autoFormService;
		historyService = autoHistoryService;
	}
	
	/**
	 * processDefinition到WfTemplate的转换
	 * @param processDefinition
	 * @return
	 */
	public static WfTemplate convertWfTemplate(ProcessDefinition processDefinition) {
		WfTemplate wfTemplate = null;
		
		if(processDefinition != null) {
			wfTemplate = new WfTemplate();
			wfTemplate.setTemplateKey(processDefinition.getKey());
			wfTemplate.setTemplateName(processDefinition.getName());
			wfTemplate.setDefinionId(processDefinition.getId());
			wfTemplate.setDescription(processDefinition.getDescription());
			wfTemplate.setTemplatePath(processDefinition.getResourceName());
			wfTemplate.setEnabled(!processDefinition.isSuspended());
		}
		return wfTemplate;
	}
	
	/**
	 * processDefinition到WfTemplate的转换
	 * @param processDefinition
	 * @return
	 */
	public static List<WfTemplate> convertWfTemplates(List<ProcessDefinition> processDefinitions) {
		List<WfTemplate> wfTemplates = new ArrayList<>();

		if(processDefinitions != null) {
			for(ProcessDefinition processDefinition : processDefinitions) {
				wfTemplates.add(convertWfTemplate(processDefinition));
			}
		}
		return wfTemplates;
	}
	
	/**
	 * FormData到WfForm的类型转换
	 * @param formData
	 * @return
	 */
	public static WfForm covertWfForm(FormData formData) {
		WfForm wfForm = null;
		
		if(formData != null) {
			wfForm = new WfForm();
			wfForm.setDeploymentId(formData.getDeploymentId());
			wfForm.setFormKey(formData.getFormKey());
			wfForm.setWfFormField(convertWfFormFields(formData.getFormProperties()));
		}
		
		return wfForm;
	}
	
	/**
	 * FormProperty到WfFormField的类型转换
	 * @param formProperties
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<WfFormField> convertWfFormFields(List<FormProperty> formProperties) {
		List<WfFormField> wfFormFields = new ArrayList<>();
		
		if(formProperties != null) {
			for(FormProperty formProperty : formProperties) {
				WfFormField wfFormField = new WfFormField();
				wfFormField.setId(formProperty.getId());
				wfFormField.setName(formProperty.getName());
				wfFormField.setType(formProperty.getType().getName());
				Map<String, String> ranges = (LinkedHashMap<String, String>) formProperty.getType().getInformation("values");
				if(ranges != null) {
					List<Map<String, String>> list = new ArrayList<>();
					for(Map.Entry<String, String> range : ranges.entrySet()) {
						Map<String, String> map = new HashMap<>();
						map.put("key", range.getKey());
						map.put("value", range.getValue());
						list.add(map);
					}
					wfFormField.setEnumRange(list);
				}
				wfFormField.setRequired(formProperty.isRequired());
				wfFormField.setWritable(formProperty.isWritable());
				wfFormField.setReadable(formProperty.isReadable());
				wfFormField.setValue(formProperty.getValue());
				wfFormFields.add(wfFormField);
			}
		}
		
		return wfFormFields;
	}
	
	/**
	 * FormProperty到WfFormField的类型转换
	 * @param formProperties
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static WfFormField convertWfFormField(FormProperty formProperty) {
		WfFormField wfFormField = null;
		
		if(formProperty != null) {
			wfFormField = new WfFormField();
			wfFormField.setId(formProperty.getId());
			wfFormField.setName(formProperty.getName());
			wfFormField.setType(formProperty.getType().getName());
			Map<String, String> ranges = (LinkedHashMap<String, String>) formProperty.getType().getInformation("values");
			if(ranges != null) {
				List<Map<String, String>> list = new ArrayList<>();
				for(Map.Entry<String, String> range : ranges.entrySet()) {
					Map<String, String> map = new HashMap<>();
					map.put("key", range.getKey());
					map.put("value", range.getValue());
					list.add(map);
				}
				wfFormField.setEnumRange(list);
			}
			wfFormField.setRequired(formProperty.isRequired());
			wfFormField.setWritable(formProperty.isWritable());
			wfFormField.setReadable(formProperty.isReadable());
			wfFormField.setValue(formProperty.getValue());
		}
		
		return wfFormField;
	}
	
	/**
	 * TaskInfo到WfTask的转换
	 * @param taks
	 * @return
	 */
	public static WfTask convertWfTask(HistoricTaskInstance task) {
		WfTask wfTask = null;
		
		if(task != null ) {
			wfTask = new WfTask();
			wfTask.setInstanceId(task.getProcessInstanceId());
			wfTask.setTaskId(task.getId());
			wfTask.setAssigne(task.getAssignee());
			wfTask.setStartTime(DateTimeUtils.formatDate(task.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
			wfTask.setEndTime(DateTimeUtils.formatDate(task.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
			wfTask.setTaskKey(task.getTaskDefinitionKey());
			wfTask.setTaskName(task.getName());
			
			// 获取历史表单数据
			List<HistoricDetail> historicDetails = historyService
					.createHistoricDetailQuery()
					.taskId(task.getId())
					.list();
			
			List<WfFormField> wfFormFields = new ArrayList<>();
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

			wfTask.setFormFields(wfFormFields);
		}
		
		return wfTask;
	}
	
	/**
	 * TaskInfo到WfTask的转换
	 * @param taks
	 * @return
	 */
	public static List<WfTask> convertWfHiscTasks(List<HistoricTaskInstance> tasks) {
		List<WfTask> wfTaskInstances = new ArrayList<>();
		
		for(HistoricTaskInstance task : tasks) {
			wfTaskInstances.add(convertWfTask(task));
		}
		
		return wfTaskInstances;
	}
	
	/**
	 * TaskInfo到WfTask的转换
	 * @param taks
	 * @return
	 */
	public static WfTask convertWfTask(Task task) {
		WfTask wfTask = null;
		
		if(task != null ) {
			wfTask = new WfTask();
			wfTask.setInstanceId(task.getProcessInstanceId());
			wfTask.setTaskId(task.getId());
			wfTask.setAssigne(task.getAssignee());
			wfTask.setStartTime(DateTimeUtils.formatDate(task.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
			wfTask.setTaskKey(task.getTaskDefinitionKey());
			wfTask.setTaskName(task.getName());
			FormData formData = formService.getTaskFormData(task.getId());
			WfForm wfForm = covertWfForm(formData);
			wfTask.setFormFields(wfForm.getWfFormFields());
		}
		
		return wfTask;
	}
	
	/**
	 * TaskInfo到WfTask的转换
	 * @param taks
	 * @return
	 */
	public static List<WfTask> convertWfTasks(List<Task> tasks) {
		List<WfTask> wfTaskInstances = new ArrayList<>();
		
		for(Task task : tasks) {
			wfTaskInstances.add(convertWfTask(task));
		}
		
		return wfTaskInstances;
	}
	
	/**
	 * ProcessInstance到WfInstance的转换
	 * @param taks
	 * @return
	 */
	public static WfInstance convertWfInstance(ProcessInstance processInstance) {
		WfInstance wfInstance = null;
		
		if(processInstance != null) {
			wfInstance = new WfInstance();
			wfInstance.setInstanceId(processInstance.getId());
			wfInstance.setBusinessKey(processInstance.getBusinessKey());
			wfInstance.setCreator(processInstance.getStartUserId());
			wfInstance.setDefinedId(processInstance.getProcessDefinitionId());
			wfInstance.setStartTime(DateTimeUtils.formatDate(processInstance.getStartTime(),"yyyy-MM-dd HH:mm:ss"));
			wfInstance.setTemplateKey(processInstance.getProcessDefinitionKey());
			wfInstance.setTemplateName(processInstance.getProcessDefinitionName());
			
			// 设置状态变量和操作变量
			Map<String, Object> variables = getVariables(processInstance.getId());
			if(variables.containsKey("#wf_operate#")) {
				wfInstance.setOperate((String)variables.get("#wf_operate#"));
			}
			if(variables.containsKey("#wf_result#")) {
				wfInstance.setResult((String)variables.get("#wf_result#"));
			}
			
			// 判断状态
			if(processInstance.isSuspended()) {
				wfInstance.setStatus("100");
			} else {
				wfInstance.setStatus("000");
			}
		}
		
		return wfInstance;
	}
	
	/**
	 * ProcessInstance到WfInstance的转换
	 * @param taks
	 * @return
	 */
	public static List<WfInstance> convertWfInstances(List<ProcessInstance> processInstances) {
		List<WfInstance> wfInstances = new ArrayList<>();
		
		if(processInstances != null) {
			for(ProcessInstance processInstance : processInstances) {
				wfInstances.add(convertWfInstance(processInstance));
			}
		}
		
		return wfInstances;
	}
	
	/**
	 * ProcessInstance到WfInstance的转换
	 * @param taks
	 * @return
	 */
	public static WfInstance convertWfInstances(HistoricProcessInstance processInstance) {
		WfInstance wfInstance = null;
		
		if(processInstance != null) {
			wfInstance = new WfInstance();
			wfInstance.setInstanceId(processInstance.getId());
			wfInstance.setBusinessKey(processInstance.getBusinessKey());
			wfInstance.setCreator(processInstance.getStartUserId());
			wfInstance.setDefinedId(processInstance.getProcessDefinitionId());
			if(processInstance.getStartTime() != null) {
				wfInstance.setStartTime(DateTimeUtils.formatDate(processInstance.getStartTime(),"yyyy-MM-dd HH:mm:ss"));
			}
			if(processInstance.getEndTime() != null) {
				wfInstance.setEndTime(DateTimeUtils.formatDate(processInstance.getEndTime(),"yyyy-MM-dd HH:mm:ss"));
			}
			wfInstance.setTemplateKey(processInstance.getProcessDefinitionKey());
			wfInstance.setTemplateName(processInstance.getProcessDefinitionName());
			
			// 设置状态变量和操作变量
			Map<String, Object> variables = getVariables(processInstance.getId());
			if(variables.containsKey("#wf_operate#")) {
				wfInstance.setOperate((String)variables.get("#wf_operate#"));
			}
			if(variables.containsKey("#wf_result#")) {
				wfInstance.setResult((String)variables.get("#wf_result#"));
				if("abort".equals(wfInstance.getResult())) {
					wfInstance.setStatus("200");
				} else {
					wfInstance.setStatus("201");
				}
			}
		}
		
		return wfInstance;
	}
	
	/**
	 * HistoricProcessInstance到WfInstance的转换
	 * @param taks
	 * @return
	 */
	public static List<WfInstance> convertWfHiscInstances(List<HistoricProcessInstance> processInstances) {
		List<WfInstance> wfInstances = new ArrayList<>();
		
		if(processInstances != null) {
			for(HistoricProcessInstance processInstance : processInstances) {
				wfInstances.add(convertWfInstances(processInstance));
			}
		}
		
		return wfInstances;
	}
	
	/**
	 * 获取实例变量合集
	 * @param instanceId
	 * @return
	 */
	public static Map<String, Object> getVariables(String instanceId) {
		Map<String, Object> variables = new HashMap<>();
		
		List<HistoricVariableInstance> list = historyService
				.createHistoricVariableInstanceQuery()
				.processInstanceId(instanceId)
				.list();
		
		for (HistoricVariableInstance variable : list) {
			variables.put(variable.getVariableName(), variable.getValue());
		}
		
		return variables;
	}

}
