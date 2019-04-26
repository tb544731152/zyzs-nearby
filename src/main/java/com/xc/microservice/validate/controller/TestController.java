package com.xc.microservice.validate.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xc.microservice.validate.model.entity.FansSession;
import com.xc.microservice.validate.model.result.Result;
import com.xc.microservice.validate.redis.RedisService;
import com.xc.microservice.validate.redis.SessionKey;
import com.xc.microservice.validate.service.CommonService;
import com.xc.microservice.validate.service.FansService;
import com.zyzs.microservice.validate.domain.user.ZyzsFans;

/**
 * 用户服务
 */
@RestController
public class TestController extends BaseController{
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	FansService fansService;
	
	/**
	 * 模拟授权 --- 
	 * 数据库中有此粉丝信息
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	
	@RequestMapping(value="/auth/test")
	@ResponseBody
	public Result<?> auth_user_center(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException{
		String openId = request.getParameter("openId");
		String appId = request.getParameter("appId");
		FansSession fans = fansService.queryByOpenId(appId,openId);
		//放入session
		if(!redisService.exists(SessionKey.session, openId)){
			redisService.set(SessionKey.session, openId,fans);
		}
		return  Result.success(openId);
	}
	
	
}
