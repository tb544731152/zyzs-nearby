package com.xc.microservice.validate.service;

import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xc.microservice.validate.redis.CodeKey;
import com.xc.microservice.validate.redis.RedisService;
import com.xc.microservice.validate.util.WxUtil;
import com.zyzs.microservice.validate.domain.user.ZyzsSide;

/**
 * 
 * @author zk_zyzs@aliyun.com
 * 2018年11月26日
 */
@Service
public class CommonService {
	/* 日志 */                   
	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	RedisService redisService;
	
	@Autowired
	private PublicNumService publicNumService;
	/**
	 * 微信授权
	 */
	public String wxAuthResult(String appId,String code){
		ZyzsSide side = publicNumService.getPublicNumByAppId(appId);
		String appsecret = side.getAppSecret();
		if(redisService.exists(CodeKey.code, code)){
			return redisService.get(CodeKey.code,code, String.class);
		}
		try {
			JSONObject data = WxUtil.analysisAuthor(appId, appsecret, code);
			logger.info("授权信息为："+data.toString());
			String openid = data.getString("openid");
			redisService.set(CodeKey.code,code, openid);
			return openid;
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
	}
	
	
	public JSONObject packagSdAddrParameter(String appId,HttpServletRequest request) throws Exception{
		//查询公众号信息
		ZyzsSide side = publicNumService.getPublicNumByAppId(appId);
		String sideid = side.getSideId();
		//获取公众号的token
		String accessToken = side.getAccessToken();
		//获取公众号tiket
		String jsApiTicket = side.getJsApiTiket();
		JSONObject jsParame = WxUtil.getParam(appId, accessToken,
				jsApiTicket, request);
		jsParame.put("sideid", sideid);
		return jsParame;
	}
	
	
}
