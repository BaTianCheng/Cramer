package com.cw.cramer.core.security;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.auth.entity.SysUser;
import com.cw.cramer.auth.service.SysUserService;
import com.google.common.collect.Lists;

@Service(value = "securityService")
public class SecurityService {
	
	@Autowired  
	private SessionDAO sessionDAO;
	
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 获取当前用户
	 * @return
	 */
	public SysUser getCurrentUser(){
		Subject currentSubject = SecurityUtils.getSubject();
		return sysUserService.getSysUser(currentSubject.getPrincipal().toString());
	}
	
	/**
	 * 获取所有在线用户
	 * @return
	 */
	public List<Session> getOnlineSessions(){
		return Lists.newArrayList(sessionDAO.getActiveSessions());
	}

}
