package com.cw.cramer.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cw.cramer.auth.entity.SysUser;
import com.cw.cramer.auth.service.SysUserService;
import com.cw.cramer.common.base.BaseController;

/**
 * 系统用户控制器
 * @author wicks
 */
@Controller
public class SysUserController extends BaseController{
	
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
		return new ModelAndView("auth/users");
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
		return this.renderSuccessJson(sysUserService.getSysUsers(pageNum, pageSize, userName));
	}
	
	/**
	 * 获取用户
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth/users/get", method=RequestMethod.POST)
	@ResponseBody
	public String getUser(HttpServletRequest request, Model model, int userId) {
		return this.renderSuccessJson(sysUserService.getSysUser(userId));
	}
	
	/**
	 * 更新用户
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth/users/update", method=RequestMethod.POST)
	@ResponseBody
	public String updateUser(HttpServletRequest request, Model model, int pageNum, int pageSize, SysUser user) {
		return this.renderSuccessJson(sysUserService.update(user));
	}
	
	/**
	 * 删除用户
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth/users/delete", method=RequestMethod.POST)
	@ResponseBody
	public String deleteUser(HttpServletRequest request, Model model, int userId) {
		return this.renderSuccessJson(sysUserService.delete(userId));
	}

}
