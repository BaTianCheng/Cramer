package com.cw.cramer.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.auth.dao.SysAuthorityDAO;
import com.cw.cramer.auth.dao.SysRoleAuthorityDAO;
import com.cw.cramer.auth.dao.SysRoleDAO;
import com.cw.cramer.auth.entity.SysAuthority;
import com.cw.cramer.auth.entity.SysAuthorityExample;
import com.cw.cramer.auth.entity.SysRole;
import com.cw.cramer.auth.entity.SysRoleAuthority;
import com.cw.cramer.auth.entity.SysRoleAuthorityExample;
import com.cw.cramer.auth.entity.SysRoleExample;
import com.cw.cramer.auth.entity.SysUser;

/**
 * 权限服务类
 * @author wicks
 */
@Service(value = "sysAuthorityService")
public class SysAuthorityService {

	@Autowired
	private SysUserService  sysUserService;
	
	@Autowired
	private SysRoleDAO sysRoleDAO;
	
	@Autowired
	private SysAuthorityDAO sysAuthorityDAO;
	
	@Autowired
	private SysRoleAuthorityDAO sysRoleAuthorityDAO;
	
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
				for(SysAuthority authority : role.getAuthorities()){
					if(!authorityIds.contains(authority.getId())){
						authorityIds.add(authority.getId());
						authorities.add(authority);
					}
				}
			}
		}
		
		return authorities;
	}
	
	/**
	 * 获取角色拥有的权限集合
	 * @param userId
	 * @return
	 */
	public List<SysAuthority> getAuthoritiesByRole(int roleId){
		return sysRoleDAO.selectByPrimaryKey(roleId).getAuthorities();
	}
	
	/**
	 * 获取所有权限
	 * @return
	 */
	public List<SysAuthority> getAuthorities(){
		SysAuthorityExample example = new SysAuthorityExample();
		example.setOrderByClause("sort");
		return sysAuthorityDAO.selectByExample(example);
	}
	
	/**
	 * 更新角色权限
	 * @param editedUser
	 * @return
	 */
	public boolean updateRoleAuthorities(int roleId, List<Integer> authorityIds){
		sysRoleAuthorityDAO.deleteByExample(new SysRoleAuthorityExample(){{this.or().andRoleIdEqualTo(roleId);}});
		if(authorityIds != null){
			for(Integer authorityId : authorityIds){
				sysRoleAuthorityDAO.insert(new SysRoleAuthority(){{this.setAuthorityId(authorityId);this.setRoleId(roleId);}});
			}
		}
		return true;
	}
	
}
