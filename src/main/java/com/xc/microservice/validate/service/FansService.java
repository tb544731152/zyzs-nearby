package com.xc.microservice.validate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xc.microservice.validate.dao.FansMapper;
import com.xc.microservice.validate.model.entity.FansSession;
import com.xc.microservice.validate.redis.RedisConstant;
import com.zyzs.microservice.validate.domain.user.ZyzsFans;

@Service
public class FansService {
	@Autowired
	FansMapper fansMapper;
	
	
	
	public FansSession queryByOpenId(String appId,String openId){
		if(appId.equals(RedisConstant.SD_APPID)){
			List<FansSession> fans =  fansMapper.sdQueryFansByOpenId(openId);
			if(fans!=null&&fans.size()>0){
				return fans.get(0);
			}
			return null;
		}
		
		if(appId.equals(RedisConstant.HLJ_APPID)){
			List<FansSession> fans =  fansMapper.hljQueryFansByOpenId(openId);
			if(fans!=null&&fans.size()>0){
				return fans.get(0);
			}
			return null;
		}
		
		if(appId.equals(RedisConstant.ZYZS_APPID)){
			List<FansSession> fans =  fansMapper.sdQueryZyzsFansByOpenId(openId);
			if(fans!=null&&fans.size()>0){
				return fans.get(0);
			}
			return null;
		}
		return null;
			
	}
	
	
}
