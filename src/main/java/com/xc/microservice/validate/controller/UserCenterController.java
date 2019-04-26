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
public class UserCenterController extends BaseController{
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	FansService fansService;
	
	/**
	 * 所有页面的授权
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	
	@RequestMapping(value="/auth/login")
	@ResponseBody
	public Result<?> auth_user_center(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException{
		String code = request.getParameter("code");
		String appId = request.getParameter("appId");
		String openId = commonService.wxAuthResult(appId, code);
		//添加至授权--session
		addSession(appId, openId);
		return  Result.success(openId);
	}
	
	public void addSession(String appid,String openid){
		FansSession fans = fansService.queryByOpenId(appid,openid);
		//放入session
		if(!redisService.exists(SessionKey.session, openid)){
			redisService.set(SessionKey.session, openid,fans);
		}
	}
	
	/**
	 * 所有页面的授权
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	
	@RequestMapping(value="/auth/get_wxauth")
	@ResponseBody
	public Result<?> get_wxauth(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException{
		/*String token = "tk"+UUID.randomUUID().toString().replace("-", "");
		String skipurl = projectUrl+"/author?token="+token;
		String authUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?"
				+ "appid="+appId+"&redirect_uri="+skipurl
				+"&response_type=code&scope=snsapi_base&"
				+ "state=STATE#wechat_redirect";*/
		String authUrl = "http://127.0.0.1/author?token=tk7f1c06d1bd4e407db2d9ab6708645848&code=071ggiZx0Ur46c11rDYx0FunZx0ggiZV";
		return  Result.success(authUrl);
	}
}
