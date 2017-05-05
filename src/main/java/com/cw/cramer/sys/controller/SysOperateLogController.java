package com.cw.cramer.sys.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cw.cramer.common.base.BaseController;
import com.cw.cramer.sys.entity.SysOperateLog;
import com.cw.cramer.sys.service.SysOperateLogService;

/**
 * 系统操作日志控制器
 * @author wicks
 */
@Controller
public class SysOperateLogController extends BaseController{
	
	@Autowired
	SysOperateLogService sysOperateLogService;
	
	/**
	 * 首页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sys/logs")
	public ModelAndView toIndex(HttpServletRequest request, Model model) {
		return new ModelAndView("sys/logs");
	}
	
	/**
	 * 获取日志列表
	 * @param request
	 * @param model
	 * @param sysOperateLog
	 * @return
	 */
	@RequestMapping(value = "/sys/logs/list")
	@ResponseBody
	public String getUsers(HttpServletRequest request, Model model, int pageNum, int pageSize, SysOperateLog sysOperateLog) {
		return this.renderSuccessJson(sysOperateLogService.getSysOperateLogs(pageNum, pageSize, sysOperateLog));
	}

}
