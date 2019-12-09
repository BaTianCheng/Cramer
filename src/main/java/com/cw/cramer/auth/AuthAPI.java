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
		if(null == userId){
			return null;
		}
		SysUser user = sysUserService.getSysUser(userId);
		if(null != user){
			return user.getName();
		} else {
			return null;
		}
	}

}
