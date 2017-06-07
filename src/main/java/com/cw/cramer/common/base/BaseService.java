package com.cw.cramer.common.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.cw.cramer.auth.AuthAPI;
import com.cw.cramer.auth.entity.SysUser;
import com.cw.cramer.core.security.SecurityService;
import com.cw.cramer.sys.SysAPI;

/**
 * 基础服务类
 * @author wicks
 */
public class BaseService {
	
	@Autowired
	private SysAPI sysAPI;
	
	@Autowired
	private AuthAPI authAPI;
	
	@Autowired
	private SecurityService securityService;
	
	public SysUser getCurrentUser(){
		return securityService.getCurrentUser();
	}
	
	public int getNextSeq(String seqName){
		return sysAPI.getNextSeq(seqName);
	}
	
	public String getUserName(int userId){
		return authAPI.getUserName(userId);
	}

}
