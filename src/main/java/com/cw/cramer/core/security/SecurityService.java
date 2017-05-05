package com.cw.cramer.core.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.auth.entity.SysAuthority;
import com.cw.cramer.auth.entity.SysUser;
import com.cw.cramer.auth.service.SysAuthorityService;
import com.cw.cramer.auth.service.SysUserService;
import com.google.common.collect.Lists;

@Service(value = "securityService")
public class SecurityService {
	
	@Autowired  
	private SessionDAO sessionDAO;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysAuthorityService sysAuthorityService;
	
	/**
	 * 获取当前用户
	 * @return
	 */
	public SysUser getCurrentUser(){
		Subject currentSubject = SecurityUtils.getSubject();
		if(currentSubject.getPrincipal() == null){
			return null;
		} else {
			return sysUserService.getSysUser(currentSubject.getPrincipal().toString());
		}
	}
	
	/**
	 * 获取当前会话
	 * @return
	 */
	public Session getCurrentSession(){
		return  SecurityUtils.getSubject().getSession();
	}
	
	/**
	 * 获取所有在线用户
	 * @return
	 */
	public List<Session> getOnlineSessions(){
		return Lists.newArrayList(sessionDAO.getActiveSessions());
	}
	
	/**
	 * 获取权限编码集合
	 * @param userId
	 * @return
	 */
	public List<String> getAuthorityCodes(int userId){
		List<String> authorityCodes = new ArrayList<String>();
		List<SysAuthority> sysAuthorities = sysAuthorityService.getUserAuthorities(userId);
		for(SysAuthority sysAuthority : sysAuthorities){
			authorityCodes.add(sysAuthority.getCode());
		}
		return authorityCodes;
	}

}
