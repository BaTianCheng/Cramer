package com.cw.test;

import java.util.Collection;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;

@RequiresPermissions("session:*")
public class aaa {
	private SessionManager sessionManager;
	
    private SessionDAO sessionDAO;
	
	public SessionDAO getSessionDAO() {
		return sessionDAO;
	}

	public void setSessionDAO(SessionDAO sessionDAO) {
		this.sessionDAO = sessionDAO;
	}

	public SessionManager getSessionManager() {
		return sessionManager;
	}

	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}
	
    public String list() {
        Collection<Session> sessions =  sessionDAO.getActiveSessions();
        return sessions.toString();
    }
    
    
	
}
