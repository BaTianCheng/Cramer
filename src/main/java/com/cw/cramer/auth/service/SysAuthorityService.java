package com.cw.cramer.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.auth.dao.SysRoleDAO;
import com.cw.cramer.auth.entity.SysAuthority;
import com.cw.cramer.auth.entity.SysRole;
import com.cw.cramer.auth.entity.SysRoleExample;
import com.cw.cramer.auth.entity.SysUser;

/**
 * 权限服务类
 * @author wicks
 */
@Service(value = "sysAuthorityService")
public class SysAuthorityService {

	@Autowired
	SysUserService  sysUserService;
	
	@Autowired
	SysRoleDAO sysRoleDAO;
	
	/**
	 * 获取用户拥有的权限集合
	 * @param userId
	 * @return
	 */
	public List<SysAuthority> getUserAuthorities(int userId){
		List<SysAuthority> authorities = new ArrayList<SysAuthority>();
		SysUser user = sysUserService.getSysUser(userId);
		if(user.getRoleIds() == null){
			return authorities;
		}
		SysRoleExample example = new SysRoleExample();
		example.or().andIdIn(user.getRoleIds());
		List<SysRole> roles = sysRoleDAO.selectByExample(example);
		List<Integer> authorityIds = new ArrayList<Integer>();
		for(SysRole role : roles){
			if(role.getAuthorities() != null){
				for(SysAuthority authority : authorities){
					if(!authorityIds.contains(authority.getId())){
						authorityIds.add(authority.getId());
						authorities.add(authority);
					}
				}
			}
		}
		
		return authorities;
	}
}
