package com.xc.microservice.validate.config.access;
import com.xc.microservice.validate.model.entity.FansSession;



public class UserContext {
	
	private static ThreadLocal<FansSession> userHolder = new ThreadLocal<FansSession>();
	
	public static void setUser(FansSession fans) {
		userHolder.set(fans);
	}
	
	public static FansSession getUser() {
		return userHolder.get();
	}

}
