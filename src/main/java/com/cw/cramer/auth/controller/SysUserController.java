package com.cw.cramer.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cw.cramer.auth.service.SysUserService;

/**
 * 系统用户控制器
 * @author wicks
 */
@Controller
public class SysUserController {
	
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 用户管理页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/users")
	public ModelAndView totest1(HttpServletRequest request, Model model) {
		return new ModelAndView("auth/table");
	}
	
	/**
	 * 获取用户列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth/users/list", method=RequestMethod.POST)
	@ResponseBody
	public String getUsers(HttpServletRequest request, Model model, int pageNum, int pageSize, String userName) {
		return JSON.toJSONString(sysUserService.getSysUsers(pageNum, pageSize, userName));
	}

}
