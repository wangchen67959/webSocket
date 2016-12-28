package com.socket.controller;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

import com.socket.util.SessionUtil;

@ServerEndpoint("/flyfishWebSocket/{userId}")
public class WebSocket {
	
	public static final Logger log = Logger.getLogger(WebSocket.class);
	
	@OnOpen
	public void open(Session session, @PathParam("userId") String userId) {
		if(!SessionUtil.hasKey(userId)) {
			SessionUtil.put(userId, session);
		}
		System.out.println(SessionUtil.get(userId));
	}

	@OnMessage
	public void inMessage(String message) {
		System.out.println(message);
	}

	@OnClose
	public void end(Session session, CloseReason reason, @PathParam("userId") String userId) {
		Session s = (Session) SessionUtil.get(userId);
		try {
			if(null == s) {
				log.error("该用户未创建session");
			}else {
				s.close();
			}
		} catch (Exception e) {
			log.error("关闭session异常"+reason.getReasonPhrase(), e);
		}
		finally{
			SessionUtil.remove(userId);
		}
	}
}
