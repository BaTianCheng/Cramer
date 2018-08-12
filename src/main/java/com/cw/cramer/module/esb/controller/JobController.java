package com.cw.cramer.module.esb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cw.cramer.common.base.BaseController;

/**
 * ESB计划任务控制器
 * @author wicks
 */
@Controller
public class JobController extends BaseController{
	
	/**
	 * 计划任务页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/esb/planningjobs")
	public ModelAndView toPlanningJobList(HttpServletRequest request, Model model) {
		return new ModelAndView("module/esb/planningjobs");
	}
	
	/**
	 * 实时任务页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/esb/regularjobs")
	public ModelAndView toRegularJobList(HttpServletRequest request, Model model) {
		return new ModelAndView("module/esb/regularjobs");
	}

}
