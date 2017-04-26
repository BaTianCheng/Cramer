package com.cw.cramer.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cw.cramer.auth.entity.SysRole;
import com.cw.cramer.auth.service.SysRoleService;
import com.cw.cramer.common.base.BaseController;

/**
 * 系统角色控制器
 * @author wicks
 */
@Controller
public class SysRoleController extends BaseController{
	
	@Autowired
	private SysRoleService sysRoleService;
	
	/**
	 * 角色管理页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth/roles")
	public ModelAndView toIndex(HttpServletRequest request, Model model) {
		return new ModelAndView("auth/roles");
	}
	
	/**
	 * 获取角色列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth/roles/list")
	@ResponseBody
	public String list(HttpServletRequest request, Model model, int pageNum, int pageSize, String roleName) {
		return this.renderSuccessJson(sysRoleService.getSysRoles(pageNum, pageSize, roleName));
	}
	
	/**
	 * 获取角色
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth/roles/get", method=RequestMethod.POST)
	@ResponseBody
	public String get(HttpServletRequest request, Model model, int roleId) {
		return this.renderSuccessJson(sysRoleService.getSysRole(roleId));
	}
	
	/**
	 * 更新角色
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth/roles/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpServletRequest request, Model model, String oper, SysRole role) {
		return this.renderSuccessJson(sysRoleService.updateInfo(role));
	}
	
	/**
	 * 增加角色
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth/roles/add", method=RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, Model model, String oper, SysRole role) {
		return this.renderSuccessJson(sysRoleService.insert(role));
	}
	
	/**
	 * 删除角色
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth/roles/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(HttpServletRequest request, Model model, int roleId) {
		return this.renderSuccessJson(sysRoleService.delete(roleId));
	}

}
