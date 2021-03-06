package com.cw.cramer.core.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cw.cramer.auth.entity.SysUser;
import com.cw.cramer.auth.service.SysUserService;
import com.cw.cramer.common.constant.UserStatusConstant;

/**
 * 安全身份管理类
 * @author wicks
 */
@Component
public class ShiroRealm extends AuthorizingRealm{
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SecurityService securityService;
	
	/**
	 * SESSION名称
	 */
	private static final String SESSIONNAME = "currentUser";

	/**
	 * 获得权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		String userName = (String)principals.getPrimaryPrincipal();
		SysUser user = sysUserService.getSysUser(userName);
		if(user != null){
			authorizationInfo.addRoles(user.getRoleCodes());
			authorizationInfo.addStringPermissions(securityService.getAuthorityCodes(user.getId()));
		}
		return authorizationInfo;
	}

	/**
	 * 验证登录
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
		String userName = (String)token.getPrincipal();
		SysUser user = sysUserService.getSysUser(userName);
		
		//没找到帐号
		if(null == user || UserStatusConstant.STATUS_DELETED == user.getStatus()) {
			throw new UnknownAccountException();
		} 
		//帐号锁定
		if(UserStatusConstant.STATUS_LOCKED == user.getStatus()) {
			throw new LockedAccountException(); 
		}
		
		//验证密码
        if(sysUserService.checkPassWord(token.getUsername(), String.valueOf(token.getPassword()))){  
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), this.getName());  
            this.setSession(SESSIONNAME, token.getUsername());  
            return authcInfo;  
        } else {
        	throw new IncorrectCredentialsException();
        }

	}
	
    /** 
     * 将一些数据放到ShiroSession中,以便于其它地方使用 
     * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到 
     */  
    private void setSession(Object key, Object value){  
        Subject currentUser = SecurityUtils.getSubject();  
        if(null != currentUser){  
            Session session = currentUser.getSession();  
            if(null != session){  
                session.setAttribute(key, value);  
            }  
        }  
    } 

}
