package com.cw.cramer.auth.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cw.cramer.auth.entity.SysRole;
import com.cw.cramer.auth.service.SysRoleService;
import com.cw.cramer.common.base.BaseController;
import com.cw.cramer.common.constant.ModuleType;
import com.cw.cramer.common.constant.OperateLogType;

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
	public String list(HttpServletRequest request, Model model, int pageNum, Integer pageSize, int departmentId, String roleName, String sortId, String sortType) {
		return this.renderSuccessJson(sysRoleService.getSysRoles(pageNum, pageSize, departmentId, roleName, sortId, sortType));
	}
	
	/**
	 * 根据部门获取角色列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth/departments/roles/list")
	@ResponseBody
	public String listByDepartment(HttpServletRequest request, Model model, int departmentId) {
		return this.renderSuccessJson(sysRoleService.getRolesByDepartment(departmentId));
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
		boolean isSuccess = sysRoleService.updateInfo(role);
		if(isSuccess){
			this.record(ModuleType.Auth, OperateLogType.Update, "修改角色："+role.getName());
		}
		return this.renderJson(isSuccess);
	}
	
	/**
	 * 增加角色
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth/roles/add", method=RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, Model model, @RequestParam("id") String id, SysRole role) {
		boolean isSuccess = sysRoleService.insert(role);
		if(isSuccess){
			this.record(ModuleType.Auth, OperateLogType.Insert, "增加角色："+role.getName());
		}
		return this.renderJson(isSuccess);
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
		boolean isSuccess = sysRoleService.delete(roleId);
		if(isSuccess){
			this.record(ModuleType.Auth, OperateLogType.Delete, "删除角色，编号："+roleId);
		}
		return this.renderJson(isSuccess);
	}
	
	/**
	 * 更新部门角色
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth/departments/roles/update")
	@ResponseBody
	public String updateRoleAuthorities(HttpServletRequest request, Model model, int departmentId, Integer[] roleIds) {
		boolean isSuccess = sysRoleService.updateDepartmentRoles(departmentId, Arrays.asList(roleIds));
		if(isSuccess){
			this.record(ModuleType.Auth, OperateLogType.Update, "更新部门角色，部门编号："+departmentId);
		}
		return this.renderJson(isSuccess);
	}

}
