package com.xc.microservice.validate.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.xc.microservice.validate.model.entity.FansSession;

@Mapper
public interface FansMapper {

	@Select("SELECT openid,unionid,zyzsId FROM oo_fans where openid=#{openID}")
	public List<FansSession> sdQueryFansByOpenId(String openID);
	
	
	@Select("SELECT openid,unionid,zyzsId  FROM oo_fans where openid=#{openID}")
	public List<FansSession> hljQueryFansByOpenId(String openID);
	
	@Select("SELECT openid,unionid,zyzsId  FROM zyzswine.oo_fans where openid=#{openID}")
	public List<FansSession> sdQueryZyzsFansByOpenId(String openID);
}
