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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xc.microservice.validate.config.access.AccessLimit;
import com.xc.microservice.validate.model.entity.FansSession;
import com.xc.microservice.validate.model.nearby.OfficialSellers;
import com.xc.microservice.validate.model.nearby.ZyzsProduct;
import com.xc.microservice.validate.model.result.Result;
import com.xc.microservice.validate.redis.RedisService;
import com.xc.microservice.validate.redis.SessionKey;
import com.xc.microservice.validate.service.CommonService;
import com.xc.microservice.validate.service.FansService;
import com.xc.microservice.validate.service.SkuService;
import com.zyzs.microservice.validate.domain.user.ZyzsFans;

/**
 * 查询产品服务
 */
@RestController
public class SKUController extends BaseController{
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	private SkuService skuService;
	
	
	/**
	 * 查询此店铺拥有的产品
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	@RequestMapping(value="/mysku/query",method = RequestMethod.GET, produces = "application/json")
	@AccessLimit(seconds=60, maxCount=5, needLogin=true)
	public Result<?> QueryMySku(Model model,FansSession fans){
		 List<ZyzsProduct>  skus = skuService.queryShopSku(fans.getZyzsId());
		return Result.success(skus);
	}
	/**
	 * 查询所有产品
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	@RequestMapping(value="/sku/query",method = RequestMethod.GET, produces = "application/json")
	@AccessLimit(seconds=60, maxCount=5, needLogin=true)
	public Result<?> QuerySku(Model model,FansSession fans){
		 List<ZyzsProduct>  skus = skuService.querySku();
		return Result.success(skus);
	}
	
	
	/**
	 * 修改商品
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	@RequestMapping(value="/sku/update",method = RequestMethod.POST, produces = "application/json")
	@AccessLimit(seconds=60, maxCount=5, needLogin=true)
	public Result<?> uploadSeller(Model model,FansSession fans, @Valid String [] pdcIds){
		Boolean flag = skuService.updateSku(fans.getZyzsId(), pdcIds);
		return Result.success(flag);
	}
	
}
