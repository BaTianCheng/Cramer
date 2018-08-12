package com.cw.cramer.module.msg.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cw.cramer.common.base.BaseController;
import com.cw.cramer.common.constant.ModuleType;
import com.cw.cramer.common.constant.OperateLogType;
import com.cw.cramer.module.msg.entity.MsgWebMail;
import com.cw.cramer.module.msg.service.MsgWebMailService;

/**
 * 站内信控制器
 * @author wicks
 */
@Controller
public class MsgWebMailController extends BaseController{
	
	@Autowired
	MsgWebMailService msgWebMailService;
	
	/**
	 * 站内信管理页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/msg/webmails")
	public ModelAndView toIndex(HttpServletRequest request, Model model) {
		return new ModelAndView("module/msg/webmails");
	}
	
	/**
	 * 获取站内信列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/msg/webmails/list")
	@ResponseBody
	public String getWebMails(HttpServletRequest request, Model model, int pageNum, int pageSize, String title, String sortId, String sortType) {
		return this.renderSuccessJson(msgWebMailService.getMsgWebMailStorages(pageNum, pageSize, title, sortId, sortType));
	}
	
	/**
	 * 获取站内信
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/msg/webmails/get", method=RequestMethod.POST)
	@ResponseBody
	public String getUser(HttpServletRequest request, Model model, int id) {
		return this.renderSuccessJson(msgWebMailService.getMsgWebMailStorage(id));
	}
	
	/**
	 * 发送公告
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/msg/webmails/send", method=RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, Model model, MsgWebMail mail) {
		boolean isSuccess = msgWebMailService.sendWebMail(mail);
		if(isSuccess){
			this.record(ModuleType.Msg, OperateLogType.Insert, "发送邮件："+mail.getTitle());
		}
		return this.renderJson(isSuccess);
	}

}
