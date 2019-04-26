package com.xc.microservice.validate.config.access;

import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.xc.microservice.validate.model.entity.AccessLog;
import com.xc.microservice.validate.model.entity.FansSession;
import com.xc.microservice.validate.model.result.CodeMsg;
import com.xc.microservice.validate.model.result.Result;
import com.xc.microservice.validate.redis.AccessKey;
import com.xc.microservice.validate.redis.RedisService;
import com.xc.microservice.validate.redis.SessionKey;
import com.xc.microservice.validate.service.AccessLogService;
import com.xc.microservice.validate.service.ApiInfoService;
import com.xc.microservice.validate.util.DateUtil;
import com.zyzs.microservice.validate.domain.user.ZyzsFans;



@Service
public class AccessInterceptor  extends HandlerInterceptorAdapter{
	
	
	@Value("${spring.application.domain}")
    private String DOMAIN;
	
	@Autowired
	private AccessLogService accessLogService;
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private ApiInfoService apiInfoService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String apiName = request.getParameter("apiName");
		if(!StringUtils.isEmpty(apiName)){
			AccessLog accessLog = new AccessLog();
			accessLog.setAccessDate(DateUtil.formatDate(new Date()));
			accessLog.setApiName(apiName);
			accessLog.setUri(request.getRequestURI());
			accessLogService.save(accessLog);
		}
		
		if(handler instanceof HandlerMethod) {
			HandlerMethod hm = (HandlerMethod)handler;
			String uri =request.getRequestURI();
			if(uri.contains("wechat/valid") || 
					uri.contains("test") || 
					uri.contains("auth") || 
					uri.contains("nolimit")){
				return true;
			}
			FansSession fans = getFans(request, response);
			AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
			UserContext.setUser(fans);
			if(accessLimit == null) {
				return true;
			}
			boolean needLogin = accessLimit.needLogin();
			int seconds = accessLimit.seconds();
			int maxCount = accessLimit.maxCount();
			String key = request.getRequestURI();
			if(needLogin) {
				if(fans == null) {
					render(response, CodeMsg.NO_LOGIN);
					return false;
				}
				key += "_" + fans.getOpenid();
			}
			AccessKey ak = AccessKey.withExpire(seconds);
			Integer count = redisService.get(ak, key, Integer.class);
	    	if(count  == null) {
	    		 redisService.set(ak, key, 1);
	    	}else if(count < maxCount) {
	    		 redisService.incr(ak, key);
	    	}else {
	    		render(response, CodeMsg.ACCESS_LIMIT_REACHED);
	    		return false;
	    	}
		}
		return true;
	}
	

	private void render(HttpServletResponse response, CodeMsg cm)throws Exception {
		response.setContentType("application/json;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		String str  = JSON.toJSONString(Result.error(cm));
		out.write(str.getBytes("UTF-8"));
		out.flush();
		out.close();
	}

	private FansSession getFans(HttpServletRequest request, HttpServletResponse response) {
		String openId = request.getParameter("openId");
		if(StringUtils.isEmpty(openId)) {
			return null;
		}
		FansSession fans = redisService.get(SessionKey.session, openId, FansSession.class);
		return fans;
	}
	
}
