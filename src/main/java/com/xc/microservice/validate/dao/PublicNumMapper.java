package com.xc.microservice.validate.dao;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.zyzs.microservice.validate.domain.user.ZyzsSide;


/**
 * 公众号--数据库操作
 * @author liuyang@ooyanjing.com
 *
 */

@Mapper
public interface PublicNumMapper {

	@Select("SELECT appid,appSecret,accessToken FROM zyzsbj.oo_public_num WHERE appId=#{appId}")
	public ZyzsSide sdQueryAccessToken(String appId);
	
	
	@Select("SELECT appid,appSecret,accessToken FROM oo_public_num WHERE appId=#{appId}")
	public ZyzsSide hljQueryAccessToken(String appId);
	
	@Select("SELECT appid,appSecret,accessToken FROM zyzswine.oo_public_num WHERE appId=#{appId}")
	public ZyzsSide sdQueryZyzsAccessToken(String appId);
	
	
	
	
	
}
