package com.cw.cramer.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cw.cramer.auth.dao.SysUserDAO;
import com.cw.cramer.auth.entity.SysUser;
import com.cw.cramer.auth.entity.SysUserExample;
import com.cw.cramer.common.util.EncryptionUtils;

/**
 * 系统操作用户服务类
 * @author wicks
 */
/**
 * 
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
	public int checkPassWord(String userName, String passWord){
		int status = 0;
		SysUser user = getSysUser(userName);
		if(user != null && user.getPassword() != null){
			if(user.getPassword().equals(EncryptionUtils.EncoderByMd5(passWord))){
				status = 1;
			}
		}
		return status;
	}

}
