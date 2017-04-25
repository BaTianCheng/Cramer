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
	@RequestMapping(value = "/auth/Departments/list")
	@ResponseBody
	public String list(HttpServletRequest request, Model model, int pageNum, int pageSize, String departmentName) {
		return this.renderSuccessJson(sysDepartmentService.getSysDepartments(pageNum, pageSize, departmentName));
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
	@RequestMapping(value = "/auth/departments/update/info", method=RequestMethod.POST)
	@ResponseBody
	public String updateUser(HttpServletRequest request, Model model, String oper, SysDepartment department) {
		switch (oper) {
			case "add":
				return this.renderSuccessJson(sysDepartmentService.insert(department));
			case "edit":
				return this.renderSuccessJson(sysDepartmentService.updateInfo(department));
			case "del":
				return this.renderSuccessJson(sysDepartmentService.delete(department.getId()));
			default:
				return this.renderFailJson("操作无效");
		}
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
		return this.renderSuccessJson(sysDepartmentService.delete(departmentId));
	}

}
