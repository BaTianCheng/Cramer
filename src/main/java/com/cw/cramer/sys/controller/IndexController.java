package com.cw.cramer.sys.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 首页控制器
 * @author wicks
 */
@Controller
public class IndexController {

	/**
	 * 首页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/", "/index"})
	public ModelAndView  toIndex(HttpServletRequest request, Model model) {
		return new ModelAndView("index");
	}
	
	/**
	 * 个人工作台
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "sys/platform")
	public ModelAndView  toLogin(HttpServletRequest request, Model model) {
		return new ModelAndView("sys/platform");
	}
	
}
