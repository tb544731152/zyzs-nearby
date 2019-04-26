package com.xc.microservice.validate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.xc.microservice.validate.model.entity.TUser;
import com.xc.microservice.validate.redis.RedisService;
import com.xc.microservice.validate.redis.SessionKey;
import com.zyzs.microservice.validate.domain.user.ZyzsFans;

@Component
public abstract class BaseController {
	
	
	@Value("${wxapp.sideId}")
	protected String sideId;
	
	@Value("${wxapp.appId}")
	protected String appId;
	
	@Value("${wxapp.projectUrl}")
	protected String projectUrl;
	
	@Autowired
	protected RedisService redisService;

	/**
	 * 判断是否登录
	 * @param errorCode
	 * @param data
	 * @return
	 */
	protected ZyzsFans isLogin(String sid) {
		ZyzsFans  fans= redisService.get(SessionKey.session, sid, ZyzsFans.class);
		return fans;
	}
}
