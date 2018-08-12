package com.cw.cramer.workflow.auth;

import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityImpl;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityImpl;

import com.cw.cramer.auth.entity.SysRole;
import com.cw.cramer.auth.entity.SysUser;

public class BaseCustomManager {

	/**
	 * 用户实体类转换
	 * @param user
	 * @return
	 */
	protected UserEntity convertUserEntity(SysUser user) {
		UserEntity userEntity = new UserEntityImpl();
		userEntity.setId(String.valueOf(user.getId()));
		userEntity.setLastName(user.getName());
		return userEntity;
	}
	
	/**
	 * 用户实体类转换
	 * @param role
	 * @return
	 */
	protected GroupEntity convertGroupEntity(SysRole role) {
		GroupEntity groupEntity = new GroupEntityImpl();
		groupEntity.setId(String.valueOf(role.getId()));
		groupEntity.setName(role.getName());
		return groupEntity;
	}
	
}
