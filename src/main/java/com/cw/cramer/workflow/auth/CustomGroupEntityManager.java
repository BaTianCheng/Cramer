package com.cw.cramer.workflow.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.auth.entity.SysRole;
import com.cw.cramer.auth.service.SysRoleService;

/**
 * 自定义用户组类（6.0可以直接注入）
 * @author wicks
 */
@Service(value="customGroupEntityManager")
public class CustomGroupEntityManager extends BaseCustomManager implements GroupEntityManager {

	@Autowired
	protected SysRoleService sysRoleService;
	
	@Override
	public GroupEntity create() {
		return null;
	}

	@Override
	public GroupEntity findById(String paramString) {
		SysRole role = sysRoleService.getSysRole(Integer.valueOf(paramString));
		return convertGroupEntity(role);
	}

	@Override
	public void insert(GroupEntity paramEntityImpl) {
	}

	@Override
	public void insert(GroupEntity paramEntityImpl, boolean paramBoolean) {
	}

	@Override
	public GroupEntity update(GroupEntity paramEntityImpl) {
		return null;
	}

	@Override
	public GroupEntity update(GroupEntity paramEntityImpl, boolean paramBoolean) {
		return null;
	}

	@Override
	public void delete(String paramString) {
	}

	@Override
	public void delete(GroupEntity paramEntityImpl) {
	}

	@Override
	public void delete(GroupEntity paramEntityImpl, boolean paramBoolean) {
	}

	@Override
	public Group createNewGroup(String paramString) {
		return null;
	}

	@Override
	public GroupQuery createNewGroupQuery() {
		return null;
	}

	@Override
	public List<Group> findGroupByQueryCriteria(GroupQueryImpl paramGroupQueryImpl, Page paramPage) {
		return null;
	}

	@Override
	public long findGroupCountByNativeQuery(Map<String, Object> paramMap) {
		return 0;
	}

	@Override
	public long findGroupCountByQueryCriteria(GroupQueryImpl paramGroupQueryImpl) {
		return 0;
	}

	@Override
	public List<Group> findGroupsByNativeQuery(Map<String, Object> paramMap, int paramInt1, int paramInt2) {
		return null;
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
	public boolean isNewGroup(Group paramGroup) {
		return false;
	}

	

}
