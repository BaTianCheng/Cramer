package com.cw.cramer.workflow.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cw.cramer.common.base.BaseController;
import com.cw.cramer.core.security.SecurityService;
import com.cw.cramer.workflow.engine.WorkFlowAPI;
import com.cw.cramer.workflow.engine.WorkFlowSystemManagerAPI;
import com.cw.cramer.workflow.engine.entity.WfForm;
import com.cw.cramer.workflow.engine.entity.WfInstance;
import com.cw.cramer.workflow.engine.entity.WfTask;
import com.cw.cramer.workflow.engine.entity.WfTemplate;
import com.cw.cramer.workflow.service.WorkflowImageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 系统工作流控制器
 * @author wicks
 */
@Controller
public class WorkflowController extends BaseController{
	
	@Autowired
	private WorkFlowAPI workFlowAPI;
	
	@Autowired
	private WorkFlowSystemManagerAPI workFlowSystemManagerAPI;
	
	@Autowired
	private WorkflowImageService workflowImageService;
	
	@Autowired
	private SecurityService securityService;
	
	/**
	 * 工作流发起页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/workflow/templates")
	public ModelAndView toIndex(HttpServletRequest request, Model model) {
		return new ModelAndView("workflow/templates");
	}
	
	/**
	 * 工作流待处理页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/workflow/undos")
	public ModelAndView toUndos(HttpServletRequest request, Model model) {
		return new ModelAndView("workflow/undos");
	}
	
	/**
	 * 工作流已处理页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/workflow/dones")
	public ModelAndView toDones(HttpServletRequest request, Model model) {
		return new ModelAndView("workflow/dones");
	}
	
	/**
	 * 工作流我的流程页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/workflow/createds")
	public ModelAndView toCreateds(HttpServletRequest request, Model model) {
		return new ModelAndView("workflow/createds");
	}
	
	/**
	 * 获取该用户的工作流模板
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/workflow/templates/list")
	@ResponseBody
	public String getTemplates(HttpServletRequest request, Model model) {
		List<WfTemplate> wfTemplates = workFlowAPI.getTemplates(securityService.getCurrentUser().getId().toString());
		return this.renderJson(wfTemplates);
	}
	
	/**
	 * 重新部署工作流
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/workflow/manager/deploy")
	@ResponseBody
	public String deploy(HttpServletRequest request, Model model) {
		workFlowSystemManagerAPI.deploy();
		workflowImageService.generateImage(request.getSession().getServletContext().getRealPath("/")+"WEB-INF/");
		return this.renderSuccessJson(null);
	}
	
	/**
	 * 获取开始表单
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/workflow/forms/start/get")
	@ResponseBody
	public String getStartForm(HttpServletRequest request, Model model, String templateKey) {
		WfForm wfForm = workFlowAPI.getTemplateStartForm(templateKey);
		return this.renderJson(wfForm);
	}
	
	/**
	 * 获取任务表单
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/workflow/forms/task/get")
	@ResponseBody
	public String getTaskForm(HttpServletRequest request, Model model, String taskId) {
		WfForm wfForm = workFlowAPI.getTaskForm(taskId);
		return this.renderJson(wfForm);
	}
	
	/**
	 * 发起工作流
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/workflow/instances/start")
	@ResponseBody
	public String startInstance(HttpServletRequest request, Model model, String templateKey, String formStr) {
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) JSON.parse(formStr);
		workFlowAPI.start(templateKey, securityService.getCurrentUser().getId().toString(), map);
		return this.renderSuccessJson(null);
	}
	
	/**
	 * 完成工作流
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/workflow/tasks/complate")
	@ResponseBody
	public String complateInstance(HttpServletRequest request, Model model, String taskId, String formStr) {
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) JSON.parse(formStr);
		workFlowAPI.complete(taskId, securityService.getCurrentUser().getId().toString(), map);
		return this.renderSuccessJson(null);
	}
	
	/**
	 * 获取待处理列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/workflow/instances/undo/list")
	@ResponseBody
	public String getUndos(HttpServletRequest request, Model model) {
		List<WfTask> tasks = workFlowAPI.getUndoTasks(securityService.getCurrentUser().getId().toString());
		//暂未做分页
		PageHelper.startPage(1, 100);
		PageInfo<WfTask> pages = new PageInfo<WfTask>(tasks);
		return this.renderSuccessJson(pages);
	}
	
	/**
	 * 获取已处理列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/workflow/instances/done/list")
	@ResponseBody
	public String getDones(HttpServletRequest request, Model model) {
		List<WfTask> tasks = workFlowAPI.getDoneTasks(securityService.getCurrentUser().getId().toString());
		//暂未做分页
		PageHelper.startPage(1, 100);
		PageInfo<WfTask> pages = new PageInfo<WfTask>(tasks);
		return this.renderSuccessJson(pages);
	}
	
	/**
	 * 获取我的申请列表（已完成中）
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/workflow/instances/creator/finish/list")
	@ResponseBody
	public String getCreatedFinishs(HttpServletRequest request, Model model) {
		List<WfInstance> instances = workFlowAPI.getFinishedInstancesByCreator(securityService.getCurrentUser().getId().toString());
		//暂未做分页
		PageHelper.startPage(1, 100);
		PageInfo<WfInstance> pages = new PageInfo<WfInstance>(instances);
		return this.renderSuccessJson(pages);
	}
	
	/**
	 * 获取我的申请列表（处理中）
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/workflow/instances/creator/undo/list")
	@ResponseBody
	public String getCreatedUndos(HttpServletRequest request, Model model) {
		List<WfInstance> instances = workFlowAPI.getUndoInstancesByCreator(securityService.getCurrentUser().getId().toString());
		//暂未做分页
		PageHelper.startPage(1, 100);
		PageInfo<WfInstance> pages = new PageInfo<WfInstance>(instances);
		return this.renderSuccessJson(pages);
	}

}
