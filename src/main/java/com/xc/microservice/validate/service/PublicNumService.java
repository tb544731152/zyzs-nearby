package com.xc.microservice.validate.service;


import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xc.microservice.validate.dao.PublicNumMapper;
import com.xc.microservice.validate.redis.RedisConstant;
import com.zyzs.microservice.validate.domain.user.ZyzsSide;

/**
 * 微信公众号服务
 * @author zk
 *
 */

@Service
@Slf4j
public class PublicNumService {
	@Autowired
	private PublicNumMapper publicNumMapper;
	
	public ZyzsSide getPublicNumByAppId(String appId){
		
		if(appId.equals(RedisConstant.SD_APPID)){
			ZyzsSide zyzsSide = publicNumMapper.sdQueryAccessToken(appId);
			if(zyzsSide!=null){
				return zyzsSide;
			}
		}
		
		if(appId.equals(RedisConstant.HLJ_APPID)){
			ZyzsSide zyzsSide = publicNumMapper.hljQueryAccessToken(appId);
			if(zyzsSide!=null){
				return zyzsSide;
			}
		}
		if(appId.equals(RedisConstant.ZYZS_APPID)){
			
			ZyzsSide zyzsSide = publicNumMapper.sdQueryZyzsAccessToken(appId);
			if(zyzsSide!=null){
				return zyzsSide;
			}
		}
		
		return null;
		
	}
}
