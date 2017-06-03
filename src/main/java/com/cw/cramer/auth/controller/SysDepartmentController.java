package com.cw.cramer.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cw.cramer.auth.entity.SysDepartment;
import com.cw.cramer.auth.service.SysDepartmentService;
import com.cw.cramer.common.base.BaseController;
import com.cw.cramer.common.constant.ModuleType;
import com.cw.cramer.common.constant.OperateLogType;

/**
 * 系统部门控制器
 * @author wicks
 */
@Controller
public class SysDepartmentController extends BaseController{
	
	@Autowired
	private SysDepartmentService sysDepartmentService;
	
	/**
	 * 部门管理页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth/departments")
	public ModelAndView toIndex(HttpServletRequest request, Model model) {
		return new ModelAndView("auth/departments");
	}
	
	/**
	 * 获取部门列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth/departments/list")
	@ResponseBody
	public String list(HttpServletRequest request, Model model, int pageNum, int pageSize, String departmentName, Integer parentId) {
		return this.renderSuccessJson(sysDepartmentService.getSysDepartments(pageNum, pageSize, departmentName, parentId));
	}
	
	/**
	 * 获取部门
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth/departments/get", method=RequestMethod.POST)
	@ResponseBody
	public String get(HttpServletRequest request, Model model, int departmentId) {
		return this.renderSuccessJson(sysDepartmentService.getSysDepartment(departmentId));
	}
	
	/**
	 * 更新部门信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth/departments/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpServletRequest request, Model model, String oper, SysDepartment department) {
		boolean isSuccess = sysDepartmentService.updateInfo(department);
		if(isSuccess){
			this.record(ModuleType.Auth, OperateLogType.Update, "修改部门："+department.getName());
		}
		return this.renderJson(isSuccess);
	}
	
	/**
	 * 新增部门
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth/departments/add", method=RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, Model model, SysDepartment department) {
		boolean isSuccess = sysDepartmentService.insert(department);
		if(isSuccess){
			this.record(ModuleType.Auth, OperateLogType.Insert, "新增部门："+department.getName());
		}
		return this.renderJson(isSuccess);
	}
	
	/**
	 * 删除部门
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth/departments/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(HttpServletRequest request, Model model, int departmentId) {
		boolean isSuccess = sysDepartmentService.delete(departmentId);
		if(isSuccess){
			this.record(ModuleType.Auth, OperateLogType.Delete, "删除部门，编号："+departmentId);
		}
		return this.renderJson(isSuccess);
	}

	/**
	 * 获取部门层级列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth/departments/level/list")
	@ResponseBody
	public String listLevel(HttpServletRequest request, Model model, int departmentId, String sort) {
		return this.renderSuccessJson(sysDepartmentService.getDepartmentLevels(departmentId, sort));
	}
	
	/**
	 * 获取部门层级列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth/departments/tree")
	@ResponseBody
	public String tree(HttpServletRequest request, Model model) {
		return this.renderSuccessJson(sysDepartmentService.getDepartmentTrees());
	}
	
}
