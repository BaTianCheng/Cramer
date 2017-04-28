package com.cw.cramer.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.auth.dao.SysDepartmentRoleDAO;
import com.cw.cramer.auth.dao.SysRoleDAO;
import com.cw.cramer.auth.entity.SysDepartmentRole;
import com.cw.cramer.auth.entity.SysDepartmentRoleExample;
import com.cw.cramer.auth.entity.SysRole;
import com.cw.cramer.auth.entity.SysRoleExample;
import com.cw.cramer.common.base.BaseService;
import com.cw.cramer.common.constant.SequenceConstant;
import com.cw.cramer.common.constant.StatusConstant;
import com.cw.cramer.common.util.DateTimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;

/**
 * 角色服务类
 * @author wicks
 */
@Service(value="sysRoleService")
public class SysRoleService extends BaseService{
	
	@Autowired
	private SysRoleDAO sysRoleDAO;
	
	@Autowired
	private SysDepartmentRoleDAO sysDepartmentRoleDAO;
	
	/**
	 * 获取角色
	 * @param id
	 * @return
	 */
	public SysRole getSysRole(int id){
		return sysRoleDAO.selectByPrimaryKey(id);
	}
	
	/**
	 * 获取角色列表
	 * @param pageNum
	 * @param pageSize
	 * @param roleName
	 * @return
	 */
	public PageInfo<SysRole> getSysRoles(int pageNum, int pageSize, String roleName) {
		PageHelper.startPage(pageNum, pageSize);
		SysRoleExample example = new SysRoleExample();
		if(!Strings.isNullOrEmpty(roleName)){
			example.or().andNameEqualTo(roleName).andStatusNotEqualTo(StatusConstant.STATUS_DELETED);
		} else {
			example.or().andStatusNotEqualTo(StatusConstant.STATUS_DELETED);
		}
		example.setOrderByClause("role_sort");
		List<SysRole> roles = sysRoleDAO.selectByExample(example);
		return new PageInfo<SysRole>(roles);
	}
	
	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	public boolean insert(SysRole role){
		role.setId(getNextSeq(SequenceConstant.SEQ_SYSROLEID));
		return sysRoleDAO.insert(role)>0 ? true : false;
	}
	
	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	public boolean update(SysRole role){
		role.setUpdateBy(this.getCurrentUser().getId());
		role.setUpdateTime(DateTimeUtils.getCurrentTime());
		return sysRoleDAO.updateByPrimaryKey(role)>0 ? true : false;
	}
	
	/**
	 * 更新角色基本信息
	 * @param editedUser
	 * @return
	 */
	public boolean updateInfo(SysRole editedRole){
		SysRole role = getSysRole(editedRole.getId());
		role.setName(editedRole.getName());
		role.setCode(editedRole.getCode());
		role.setStatus(editedRole.getStatus());
		role.setRemarks(editedRole.getRemarks());
		return update(role);
	}
	
	/**
	 * 删除角色(更新标志位)
	 * @param userId
	 * @return
	 */
	public boolean delete(int id){
		SysRole role = getSysRole(id);
		if(role != null){
			role.setStatus(StatusConstant.STATUS_DELETED);
			return sysRoleDAO.updateByPrimaryKey(role)>0 ? true : false;
		} else {
			return false;
		}
	}
	
	/**
	 * 根据部门获取角色
	 * @param departmentId
	 * @return
	 */
	public List<SysRole> getRolesByDepartment(int departmentId){
		SysDepartmentRoleExample departmentRoleExample = new SysDepartmentRoleExample();
		departmentRoleExample.or().andDepartmentIdEqualTo(departmentId);
		List<SysDepartmentRole> departmentRoles = sysDepartmentRoleDAO.selectByExample(departmentRoleExample);
		List<Integer> roleIds = new ArrayList<Integer>();
		for(SysDepartmentRole departmentRole : departmentRoles){
			roleIds.add(departmentRole.getRoleId());
		}
		SysRoleExample roleExample = new SysRoleExample();
		roleExample.or().andIdIn(roleIds);
		List<SysRole> roles = sysRoleDAO.selectByExample(roleExample);
		return roles;
	}

}
