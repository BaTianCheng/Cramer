package com.cw.cramer.workflow.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.auth.entity.SysRole;
import com.cw.cramer.auth.entity.SysUser;
import com.cw.cramer.auth.service.SysRoleService;
import com.cw.cramer.auth.service.SysUserService;

/**
 * 自定义用户类（6.0可以直接注入）
 * @author wicks
 */
@Service(value="customUserEntityManager")
public class CustomUserEntityManager extends BaseCustomManager implements UserEntityManager{
	
	@Autowired
	protected SysUserService sysUserService;
	
	@Autowired
	protected SysRoleService sysRoleService;

	@Override
	public UserEntity create() {
		return null;
	}

	@Override
	public UserEntity findById(String paramString) {
		SysUser user = sysUserService.getSysUser(Integer.valueOf(paramString));
		return convertUserEntity(user);
	}

	@Override
	public void insert(UserEntity paramEntityImpl) {
	}

	@Override
	public void insert(UserEntity paramEntityImpl, boolean paramBoolean) {
	}

	@Override
	public UserEntity update(UserEntity paramEntityImpl) {
		return null;
	}

	@Override
	public UserEntity update(UserEntity paramEntityImpl, boolean paramBoolean) {
		return null;
	}

	@Override
	public void delete(String paramString) {
	}

	@Override
	public void delete(UserEntity paramEntityImpl) {
	}

	@Override
	public void delete(UserEntity paramEntityImpl, boolean paramBoolean) {
	}

	@Override
	public User createNewUser(String paramString) {
		return null;
	}

	@Override
	public void updateUser(User paramUser) {
	}

	@Override
	public List<User> findUserByQueryCriteria(UserQueryImpl paramUserQueryImpl, Page paramPage) {
		return null;
	}

	@Override
	public long findUserCountByQueryCriteria(UserQueryImpl paramUserQueryImpl) {
		return 0;
	}

	@Override
	public List<Group> findGroupsByUser(String paramString) {
		List<SysRole> roles = sysRoleService.getRolesByUser(Integer.valueOf(paramString));
		List<Group> groups = new ArrayList<>();
		for(SysRole role : roles){
			groups.add(convertGroupEntity(role));
		}
		
		return groups;
	}

	@Override
	public UserQuery createNewUserQuery() {
		return null;
	}

	@Override
	public Boolean checkPassword(String paramString1, String paramString2) {
		return null;
	}

	@Override
	public List<User> findUsersByNativeQuery(Map<String, Object> paramMap, int paramInt1, int paramInt2) {
		return null;
	}

	@Override
	public long findUserCountByNativeQuery(Map<String, Object> paramMap) {
		return 0;
	}

	@Override
	public boolean isNewUser(User paramUser) {
		return false;
	}

	@Override
	public Picture getUserPicture(String paramString) {
		return null;
	}

	@Override
	public void setUserPicture(String paramString, Picture paramPicture) {
	}

	@Override
	public void deletePicture(User paramUser) {
	}

}
