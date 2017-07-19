package com.cw.cramer.module.esb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cw.cramer.common.base.BaseController;

/**
 * ESB服务调用控制器
 * @author wicks
 */
@Controller
public class InvokeController extends BaseController{

	/**
	 * 服务调用页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/esb/invokes")
	public ModelAndView toIndex(HttpServletRequest request, Model model) {
		return new ModelAndView("module/esb/invokes");
	}

}
