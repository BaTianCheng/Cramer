package com.cw.cramer.module.esb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cw.cramer.common.base.BaseController;

/**
 * ESB管理控制器
 * @author wicks
 */
@Controller
public class MangerController extends BaseController{
	
	/**
	 * 服务监控页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/esb/monitor")
	public ModelAndView toMonitor(HttpServletRequest request, Model model) {
		return new ModelAndView("module/esb/monitor");
	}
	
	/**
	 * 历史记录页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/esb/history")
	public ModelAndView toHistory(HttpServletRequest request, Model model) {
		return new ModelAndView("module/esb/history");
	}
	

}
