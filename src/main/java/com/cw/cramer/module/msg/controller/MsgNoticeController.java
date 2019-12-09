package com.cw.cramer.module.msg.controller;

import java.text.MessageFormat;

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
import com.cw.cramer.module.msg.entity.MsgNotice;
import com.cw.cramer.module.msg.service.MsgNoticeService;

/**
 * 通知公告控制器
 * @author wicks
 */
@Controller
public class MsgNoticeController extends BaseController{
	
	@Autowired
	MsgNoticeService msgNoticeService;
	
	/**
	 * 通知公告管理页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/msg/notifys")
	public ModelAndView toIndex(HttpServletRequest request, Model model) {
		return new ModelAndView("module/msg/notifys");
	}
	
	/**
	 * 获取公告列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/msg/notifys/list")
	@ResponseBody
	public String getUsers(HttpServletRequest request, Model model, int pageNum, int pageSize, String title, String sortId, String sortType) {
		return this.renderSuccessJson(msgNoticeService.getMsgNotices(pageNum, pageSize, title, sortId, sortType));
	}
	
	/**
	 * 获取公告
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/msg/notifys/get", method=RequestMethod.POST)
	@ResponseBody
	public String getUser(HttpServletRequest request, Model model, int id) {
		return this.renderSuccessJson(msgNoticeService.getMsgNotice(id));
	}
	
	/**
	 * 更新公告信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/msg/notifys/update", method=RequestMethod.POST)
	@ResponseBody
	public String updateUser(HttpServletRequest request, Model model, String oper, MsgNotice notice) {
		boolean isSuccess = msgNoticeService.updateInfo(notice);
		if(isSuccess){
			this.record(ModuleType.Auth, OperateLogType.Update, MessageFormat.format("修改公告：{0}", notice.getTitle()));
		}
		return this.renderJson(isSuccess);
	}
	
	/**
	 * 添加公告
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/msg/notifys/add", method=RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, Model model, MsgNotice notice) {
		boolean isSuccess = msgNoticeService.insert(notice);
		if(isSuccess){
			this.record(ModuleType.Auth, OperateLogType.Insert, MessageFormat.format("新增公告：{0}", notice.getTitle()));
		}
		return this.renderJson(isSuccess);
	}
	
	/**
	 * 删除公告
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/msg/notifys/delete", method=RequestMethod.POST)
	@ResponseBody
	public String deleteUser(HttpServletRequest request, Model model, int notifyId) {
		boolean isSuccess = msgNoticeService.delete(notifyId);
		if(isSuccess){
			this.record(ModuleType.Msg, OperateLogType.Delete, MessageFormat.format("删除公告：{0}", notifyId));
		}
		return this.renderJson(isSuccess);
	}

}
