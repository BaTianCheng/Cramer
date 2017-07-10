package com.cw.cramer.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.auth.entity.SysUser;
import com.cw.cramer.auth.service.SysUserService;

/**
 * 权限管理API
 * @author wicks
 */
@Service(value = "authAPI")
public class AuthAPI {
	
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 获取用户名
	 * @param userId
	 * @return
	 */
	public String getUserName(Integer userId){
		if(userId == null){
			return null;
		}
		SysUser user = sysUserService.getSysUser(userId);
		if(user != null){
			return user.getName();
		} else {
			return null;
		}
	}

}
