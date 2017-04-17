package com.cw.cramer.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cw.cramer.common.base.BaseController;
import com.cw.cramer.common.util.LogUtils;

/**
 * 权限总控制器
 * @author wicks
 */
@Controller
public class AuthController extends BaseController{
	
	/**
	 * 登录页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login")
	public ModelAndView toLogin(HttpServletRequest request, Model model) {
		return new ModelAndView("login");
	}
	
	/**
	 * 登录认证
	 * @param request
	 * @param model
	 * @param userName
	 * @param passWord
	 * @return
	 */
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	@ResponseBody
	public String checkLogin(HttpServletRequest request, Model model, String userName, String passWord) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);

		try {
			subject.login(token);
			return this.renderSuccessJson(null);
		}catch (UnknownAccountException e) {
			LogUtils.error("login faile: " + e);
			return this.renderFailJson(null);
		}catch (LockedAccountException e) {
			LogUtils.error("login faile: " + e);
			return this.renderFailJson(null);
		}catch (IncorrectCredentialsException e) {
			LogUtils.error("login faile: " + e);
			return this.renderFailJson(null);
		}catch (Exception e) {
			LogUtils.error("login faile: " + e);
			return this.renderFailJson(null);
		}
	}

}
