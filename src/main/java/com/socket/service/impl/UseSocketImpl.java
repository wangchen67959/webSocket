package com.socket.service.impl;

import javax.websocket.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.socket.service.UseSocket;
import com.socket.util.SessionUtil;

public class UseSocketImpl implements UseSocket{

	public static final Log log = LogFactory.getLog(UseSocketImpl.class);
	
	public void sedMessage(String userId, String data){
		Session session = (Session) SessionUtil.get(userId);
		try {
			if(null == session) {
				log.error("该用户未创建session");
			}else {
				synchronized (session) {
					session.getBasicRemote().sendText(data);
				}
			}
		} catch (Exception e) {
			SessionUtil.remove(userId);
			log.error("发送消息异常", e);
		}
	}

	public Boolean hasSession(String userId) {
		return SessionUtil.isConnection(userId);
	}
}
