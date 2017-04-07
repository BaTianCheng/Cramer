package com.cw.cramer.auth;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cw.cramer.auth.dao.SysUserDAO;

@Controller
public class IndexController {

	/**
	 * 页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/")
	public ModelAndView  toIndex(HttpServletRequest request, Model model) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");

		try {
			subject.login(token);
			System.out.println("login sucess");
		} catch (AuthenticationException e) {
			System.out.println("login faile: " + e);
		}
		
		return new ModelAndView("index");
	}
	
	/**
	 * 页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/security/login")
	public ModelAndView  toLogin(HttpServletRequest request, Model model) {
		return new ModelAndView("login");
	}
	
}
