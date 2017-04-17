package com.cw.cramer.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.auth.dao.SysUserDAO;
import com.cw.cramer.auth.entity.SysUser;
import com.cw.cramer.auth.entity.SysUserExample;
import com.cw.cramer.common.util.EncryptionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;

/**
 * 系统操作用户服务类
 * @author wicks
 */
@Service(value="sysUserService")
public class SysUserService {
	
	@Autowired
	private SysUserDAO sysUserDAO;
	
	/**
	 * 获取用户
	 * @param userName
	 * @return
	 */
	public SysUser getSysUser(String userName){
		SysUserExample example = new SysUserExample();
		example.or().andNameEqualTo(userName);
		List<SysUser> users = sysUserDAO.selectByExample(example);
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
	public SysUser getSysUser(int userId){
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
	 * @return
	 */
	public PageInfo<SysUser> getSysUsers(int pageNum, int pageSize, String userName) {
		PageHelper.startPage(pageNum, pageSize);
		SysUserExample example = new SysUserExample();
		if(!Strings.isNullOrEmpty(userName)){
			example.or().andNameEqualTo(userName);
		}
		List<SysUser> users = sysUserDAO.selectByExample(example);
		return new PageInfo<SysUser>(users);
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public boolean insert(SysUser user){
		return sysUserDAO.insert(user)>0 ? true : false;
	}
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	public boolean update(SysUser user){
		return sysUserDAO.updateByPrimaryKey(user)>0 ? true : false;
	}
	
	/**
	 * 删除用户
	 * @param user
	 * @return
	 */
	public boolean delete(int userId){
		return sysUserDAO.deleteByPrimaryKey(userId)>0 ? true : false;
	}

}
