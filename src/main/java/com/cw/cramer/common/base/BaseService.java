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
	
	/**
	 * 获取当前用户
	 * @return
	 */
	public SysUser getCurrentUser(){
		return securityService.getCurrentUser();
	}
	
	/**
	 * 获取下一个序列
	 * @param seqName
	 * @return
	 */
	public int getNextSeq(String seqName){
		return sysAPI.getNextSeq(seqName);
	}
	
	/**
	 * 获取用户名称
	 * @param userId
	 * @return
	 */
	public String getUserName(Integer userId){
		return authAPI.getUserName(userId);
	}

}
