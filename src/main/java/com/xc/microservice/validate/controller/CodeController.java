package com.xc.microservice.validate.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xc.microservice.validate.config.access.AccessLimit;
import com.xc.microservice.validate.model.entity.FansSession;
import com.xc.microservice.validate.model.nearby.OfficialSellers;
import com.xc.microservice.validate.model.nearby.ZyzsProduct;
import com.xc.microservice.validate.model.result.CodeMsg;
import com.xc.microservice.validate.model.result.Result;
import com.xc.microservice.validate.redis.RedisService;
import com.xc.microservice.validate.redis.SessionKey;
import com.xc.microservice.validate.service.CodeService;
import com.xc.microservice.validate.service.CommonService;
import com.xc.microservice.validate.service.FansService;
import com.xc.microservice.validate.service.SkuService;
import com.zyzs.microservice.validate.domain.user.ZyzsFans;

/**
 * 查询产品服务
 */
@RestController
public class CodeController extends BaseController{
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	private CodeService codeService;
	
	
	/**
	 * 发送验证码
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	@RequestMapping(value="/zyzs/getCode",method = RequestMethod.GET, produces = "application/json")
	@AccessLimit(seconds=60, maxCount=5, needLogin=true)
	public Result<?> QueryMySku(Model model,FansSession fans,@RequestParam String phone){
		CodeMsg msg = codeService.generateCode(phone);
		return Result.msg(msg);
	}
	/**
	 * 对比验证码
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	@RequestMapping(value="/zyzs/compareCode",method = RequestMethod.GET, produces = "application/json")
	@AccessLimit(seconds=60, maxCount=5, needLogin=true)
	public Result<?> QuerySku(Model model,FansSession fans,@RequestParam String phone,@RequestParam String code){
		CodeMsg msg = codeService.compareCode(phone, code);
		return Result.msg(msg);
	}
	
	
}
