package com.cw.cramer.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cw.cramer.auth.dao.SysUserDAO;
import com.cw.cramer.auth.dao.SysUserDepartmentDAO;
import com.cw.cramer.auth.dao.SysUserRoleDAO;
import com.cw.cramer.auth.entity.SysUser;
import com.cw.cramer.auth.entity.SysUserDepartment;
import com.cw.cramer.auth.entity.SysUserDepartmentExample;
import com.cw.cramer.auth.entity.SysUserExample;
import com.cw.cramer.auth.entity.SysUserRole;
import com.cw.cramer.auth.entity.SysUserRoleExample;
import com.cw.cramer.common.base.BaseService;
import com.cw.cramer.common.constant.SequenceConstant;
import com.cw.cramer.common.constant.StatusConstant;
import com.cw.cramer.common.util.DateTimeUtils;
import com.cw.cramer.common.util.EncryptionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;

/**
 * 系统操作用户服务类
 * @author wicks
 */
@Service(value="sysUserService")
public class SysUserService extends BaseService{
	
	@Autowired
	private SysUserDAO sysUserDAO;
	
	@Autowired
	private SysUserDepartmentDAO sysUserDepartmentDAO;
	
	@Autowired
	private SysUserRoleDAO sysUserRoleDAO;
		
	/**
	 * 获取用户
	 * @param userName
	 * @return
	 */
	public SysUser getSysUser(String userName){
		SysUserExample example = new SysUserExample();
		example.or().andNameEqualTo(userName);

		List<SysUser> users =  sysUserDAO.selectByExample(example);
		if(users.isEmpty()){
			return null;
		} else {
			return users.get(0);
		}
	}
	
	/**
	 * 获取用户
	 * @param userId
	 * @return
	 */
	@Cacheable(value = "SysUserCache", key = "#userId")
	public SysUser getSysUser(Integer userId){
		return sysUserDAO.selectByPrimaryKey(userId);
	}
	
	/**
	 * 检验密码是否正确
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public boolean checkPassWord(String userName, String passWord){
		SysUser user = getSysUser(userName);
		if(user != null && user.getPassword() != null){
			if(user.getPassword().equals(EncryptionUtils.EncoderByMd5(passWord))){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 获取用户列表
	 * @param pageNum
	 * @param pageSize
	 * @param userName
	 * @param sortId
	 * @param sortType
	 * @return
	 */
	public PageInfo<SysUser> getSysUsers(int pageNum, int pageSize, String userName, String sortId, String sortType) {
		PageHelper.startPage(pageNum, pageSize);
		SysUserExample example = new SysUserExample();
		String sortStr = "user.sort asc, user.id asc";
		if(!Strings.isNullOrEmpty(userName)){
			example.or().andNameEqualTo(userName).andStatusNotEqualTo(StatusConstant.STATUS_DELETED);
		} else {
			example.or().andStatusNotEqualTo(StatusConstant.STATUS_DELETED);
		}
		if(!Strings.isNullOrEmpty(sortId)){
			sortStr = sortId+" "+sortType+", " + sortStr;
		}
		example.setOrderByClause(sortStr);
		List<Integer> ids = sysUserDAO.selectIdByExample(example);
		SysUserExample exampleId = new SysUserExample();
		if(ids.size() > 0){
			exampleId.or().andIdIn(ids);
		} else {
			return new PageInfo<SysUser>(new ArrayList<SysUser>());
		}
		exampleId.setOrderByClause(sortStr);
		List<SysUser> users = sysUserDAO.selectByExample(exampleId);
		return new PageInfo<SysUser>(users);
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public boolean insertInfo(SysUser user){
		user.setId(getNextSeq(SequenceConstant.SEQ_SYSUSERID));
		//密码加密
		if(!Strings.isNullOrEmpty(user.getPassword())){
			user.setPassword(EncryptionUtils.EncoderByMd5(user.getPassword()));
		}
		//自动排序
		if(user.getSort() == null){
			user.setSort(sysUserDAO.selectNextSortId());
		}
		//添加机构关联
		sysUserDepartmentDAO.insert(new SysUserDepartment(){{this.setUserId(user.getId());this.setDepartmentId(user.getDepartmentId());}});
		//添加角色关联
		if(user.getRoleIds() != null){
			for(Integer roleId : user.getRoleIds()){
				sysUserRoleDAO.insert(new SysUserRole(){{this.setUserId(user.getId());this.setRoleId(roleId);}});
			}
		}
		return sysUserDAO.insert(user)>0 ? true : false;
	}
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@CachePut(value = "SysUserCache", key = "#user.getId()")
	public boolean update(SysUser user){
		user.setUpdateBy(this.getCurrentUser().getId());
		user.setUpdateTime(DateTimeUtils.getCurrentTime());
		return sysUserDAO.updateByPrimaryKey(user)>0 ? true : false;
	}
	
	/**
	 * 更新用户密码
	 * @param userId
	 * @param newPassword
	 * @return
	 */
	public boolean updatePassword(String newPassword){
		SysUser user = getSysUser(this.getCurrentUser().getId());
		user.setPassword(EncryptionUtils.EncoderByMd5(newPassword));
		return update(user);
	}
	
	/**
	 * 更新用户密码
	 * @param userId
	 * @param newPassword
	 * @return
	 */
	public boolean updatePassword(int userId, String newPassword){
		SysUser user = getSysUser(userId);
		user.setPassword(EncryptionUtils.EncoderByMd5(newPassword));
		return update(user);
	}
	
	/**
	 * 更新用户基本信息
	 * @param editedUser
	 * @return
	 */
	public boolean updateInfo(SysUser editedUser){
		SysUser user = getSysUser(editedUser.getId());
		user.setName(editedUser.getName());
		user.setStatus(editedUser.getStatus());
		user.setRemarks(editedUser.getRemarks());
		user.setDepartmentId(editedUser.getDepartmentId());
		user.setRoleIds(editedUser.getRoleIds());
		user.setSort(editedUser.getSort());
		sysUserDepartmentDAO.deleteByExample(new SysUserDepartmentExample(){{this.or().andUserIdEqualTo(user.getId());}});
		sysUserDepartmentDAO.insert(new SysUserDepartment(){{this.setUserId(user.getId());this.setDepartmentId(user.getDepartmentId());}});
		sysUserRoleDAO.deleteByExample(new SysUserRoleExample(){{this.or().andUserIdEqualTo(user.getId());}});
		if(user.getRoleIds() != null){
			for(Integer roleId : user.getRoleIds()){
				sysUserRoleDAO.insert(new SysUserRole(){{this.setUserId(user.getId());this.setRoleId(roleId);}});
			}
		}
		return update(user);
	}
	
	/**
	 * 删除用户(更新标志位)
	 * @param userId
	 * @return
	 */
	@CacheEvict(value = "SysUserCache", key = "#userId")
	public boolean delete(int userId){
		SysUser user = getSysUser(userId);
		if(user != null){
			user.setStatus(StatusConstant.STATUS_DELETED);
			return sysUserDAO.updateByPrimaryKey(user)>0 ? true : false;
		} else {
			return false;
		}
	}
	
	/**
	 * 根据部门获取用户
	 * @param departmentId
	 * @return
	 */
	public List<SysUser> getUsersByDepartment(int departmentId){
		SysUserDepartmentExample userDepartmentExample = new SysUserDepartmentExample();
		userDepartmentExample.or().andDepartmentIdEqualTo(departmentId);
		List<SysUserDepartment> userDepartments = sysUserDepartmentDAO.selectByExample(userDepartmentExample);
		List<Integer> userIds = new ArrayList<Integer>();
		for(SysUserDepartment userDepartment : userDepartments){
			userIds.add(userDepartment.getUserId());
		}
		SysUserExample userExample = new SysUserExample();
		userExample.or().andIdIn(userIds);
		userExample.setOrderByClause("user.sort, user.id");
		List<SysUser> users = sysUserDAO.selectByExample(userExample);
		return users;
	}
	
	/**
	 * 根据角色获取用户
	 * @param roleId
	 * @return
	 */
	public List<SysUser> getUsersByRole(int roleId){
		SysUserRoleExample userRoleExample = new SysUserRoleExample();
		userRoleExample.or().andRoleIdEqualTo(roleId);
		List<SysUserRole> userRoles = sysUserRoleDAO.selectByExample(userRoleExample);
		List<Integer> userIds = new ArrayList<Integer>();
		for(SysUserRole userRole : userRoles){
			userIds.add(userRole.getUserId());
		}
		SysUserExample userExample = new SysUserExample();
		userExample.or().andIdIn(userIds);
		userExample.setOrderByClause("user.sort, user.id");
		List<SysUser> users = sysUserDAO.selectByExample(userExample);
		return users;
	}
	
}
