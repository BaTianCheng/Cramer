package com.cw.cramer.auth.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cw.cramer.auth.entity.SysUser;
import com.cw.cramer.auth.service.SysAuthorityService;
import com.cw.cramer.common.base.BaseController;
import com.cw.cramer.common.constant.CommonConstant;
import com.cw.cramer.common.constant.ModuleType;
import com.cw.cramer.common.constant.OperateLogType;
import com.cw.cramer.common.util.LogUtils;
import com.cw.cramer.core.security.SecurityService;

/**
 * 权限总控制器
 * @author wicks
 */
@Controller
public class AuthController extends BaseController{
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private SysAuthorityService sysAuthorityService;
	
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
			SysUser user = securityService.getCurrentUser();
			this.record(ModuleType.Auth, OperateLogType.Login_In, "登录成功");
			Map<String, Object> result = new HashMap<>();
			result.put("auth", securityService.getAuthorityCodes(user.getId()));
			result.put("userName", user.getName());
			result.put("params", CommonConstant.CONFIG_PARAMS);
			return this.renderSuccessJson(result);
		}catch (UnknownAccountException e) {
			LogUtils.error("用户不存在: ",e);
			return this.renderFailJson();
		}catch (LockedAccountException e) {
			LogUtils.error("用户被锁定: ",e);
            return this.renderFailJson();
		}catch (IncorrectCredentialsException e) {
			LogUtils.error("密码错误: ",e);
            return this.renderFailJson();
		}catch (Exception e) {
			LogUtils.error("登陆失败: ",e);
            return this.renderFailJson();
		}
	}
	
	/**
	 * 获取本人权限
	 * @param request
	 * @param model
	 * @param userName
	 * @param passWord
	 * @return
	 */
	@RequestMapping(value = "/auth/authorities/current", method=RequestMethod.POST)
	@ResponseBody
	public String getAuthorities(HttpServletRequest request, Model model) {
		SysUser user = securityService.getCurrentUser();
		if(user == null){
			return this.renderFailJson(null);
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("user", user);
			map.put("auth", securityService.getAuthorityCodes(user.getId()));
			return this.renderSuccessJson(map);
		}
	}
	
	/**
	 * 获取全部权限
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth/authorities/list")
	@ResponseBody
	public String list(HttpServletRequest request, Model model) {
		return this.renderSuccessJson(sysAuthorityService.getAuthorities());
	}
	
	/**
	 * 获取角色权限
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth/roles/authorities/list")
	@ResponseBody
	public String listRoleAuthorities(HttpServletRequest request, Model model, int roleId) {
		return this.renderSuccessJson(sysAuthorityService.getAuthoritiesByRole(roleId));
	}
	
	/**
	 * 更新角色权限
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auth/roles/authorities/update")
	@ResponseBody
	public String updateRoleAuthorities(HttpServletRequest request, Model model, int roleId, Integer[] authorityIds) {
		boolean isSuccess = sysAuthorityService.updateRoleAuthorities(roleId, Arrays.asList(authorityIds));
		if(isSuccess){
			this.record(ModuleType.Auth, OperateLogType.Update, "更新角色权限");
		}
		return this.renderJson(isSuccess);
	}

}
