package com.cw.cramer.auth;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cw.cramer.common.util.LogUtils;

@Controller
public class AuthController {
	@Autowired  
	private SessionDAO sessionDAO;  
	
	/**
	 * 页面
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
			return "1";
		}catch (UnknownAccountException e) {
			LogUtils.error("login faile: " + e);
			return "-3";
		}catch (LockedAccountException e) {
			LogUtils.error("login faile: " + e);
			return "-2";
		}catch (IncorrectCredentialsException e) {
			LogUtils.error("login faile: " + e);
			return "-1";
		}catch (Exception e) {
			LogUtils.error("login faile: " + e);
			return "0";
		}
	}
	
	/**
	 * 获取当前用户
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth/getUser")
	@ResponseBody
	public String getCurrentUser(HttpServletRequest request, Model model) {
		Subject currentUser = SecurityUtils.getSubject();//获取当前用户
		Collection<Session> sessions = sessionDAO.getActiveSessions();  
		System.out.println(sessions);
		return currentUser.getPrincipal().toString();
	}
	
	/**
	 * 页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/users")
	public ModelAndView totest1(HttpServletRequest request, Model model) {
		return new ModelAndView("auth/table");
	}

}
