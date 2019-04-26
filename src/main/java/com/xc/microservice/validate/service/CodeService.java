package com.xc.microservice.validate.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xc.microservice.validate.model.result.CodeMsg;
import com.xc.microservice.validate.redis.MsgKey;
import com.xc.microservice.validate.redis.RedisService;
import com.xc.microservice.validate.util.sendMsg.SendMessageUtils;

@Service
public class CodeService {
	
	@Autowired
	RedisService redisService;
	
	public CodeMsg generateCode(String phone){
		if(redisService.exists(MsgKey.code, phone)){
			return CodeMsg.MSG_NOT_OVER;
		}
		String code = SendMessageUtils.generateCode();
		redisService.set(MsgKey.code, phone, code);
		String res = SendMessageUtils.sendMessage(phone, code);
		if(res.equals("ok")){
			return  CodeMsg.MSG_SEND_SUCCESS;
		}
		return  CodeMsg.MSG_SEND_FAIL;
		
	}
	
	public CodeMsg compareCode(String phone,String code){
		if(redisService.exists(MsgKey.code, phone)){
			String redis_code = redisService.get(MsgKey.code, phone, String.class);
			if(redis_code.equals(code)){
				return CodeMsg.SUCCESS;
			}else{
				return CodeMsg.MSG_VALIDATE_FAIL;
			}
		}else{
			return CodeMsg.MSG_VALIDATE_FAIL;
		}
		
	}
	
}
