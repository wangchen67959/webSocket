package com.socket.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {
	private static Map<String, Object> map = new ConcurrentHashMap<String, Object>();
	
	public static void put(String key, Object val){
		map.put(key,val);
	}
	
	public static Boolean isConnection(String userId){
		Boolean flag = true;
		if(null == map.get(userId)){
			flag = false;
		}
		return flag;
	}
	public static Boolean hasKey(String userId){
		return map.containsKey(userId);
	}
	
	public static void remove(String userId){
		map.remove(userId);
	}
	public static Object get(String userId) {
		return map.get(userId);
	}
}
