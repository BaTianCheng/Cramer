package com.cw.test;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

public class MySessionListener implements SessionListener{
	

	public void onStart(Session session) {//会话创建时触发
		System.out.println("会话创建：" + session.getId());
	}

	public void onExpiration(Session session) {//会话过期时触发
		System.out.println("会话过期：" + session.getId());
	}

	public void onStop(Session session) {//退出/会话过期时触发
		System.out.println("会话停止：" + session.getId());
	}

}
