package com.cw.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.DefaultSecurityManager;


public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro.ini");

		//2、得到SecurityManager实例并绑定给SecurityUtils
		DefaultSecurityManager securityManager = (DefaultSecurityManager) factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		securityManager.setSessionManager(new DefaultSessionManager());
		DefaultSessionManager sm = (DefaultSessionManager) securityManager.getSessionManager();

		//3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

		try {
			subject.login(token);
			System.out.println("login sucess");
		} catch (AuthenticationException e) {
			System.out.println("login faile: " + e);
		}

		System.out.println(sm.getSessionDAO().getActiveSessions());

	}

}
