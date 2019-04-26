package com.xc.microservice.validate.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xc.microservice.validate.config.access.AccessLimit;
import com.xc.microservice.validate.model.entity.FansSession;
import com.xc.microservice.validate.model.nearby.OfficialSellers;
import com.xc.microservice.validate.model.result.Result;
import com.xc.microservice.validate.model.vm.QueryNearyVM;
import com.xc.microservice.validate.model.vm.ShopVM;
import com.xc.microservice.validate.redis.RedisService;
import com.xc.microservice.validate.service.CommonService;
import com.xc.microservice.validate.service.OfficialService;
import com.xc.microservice.validate.service.SmokerShareService;
import com.zyzs.microservice.validate.domain.nearby.ZyzsSmokerShareSellers;

/**
 * 官方认证零售户
 */
@RestController
public class OfficialController{
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	private OfficialService officialService;
	
	@Autowired
	private SmokerShareService smokerShareService;
	
	/**
	 * 官方认证零售户上传
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	@RequestMapping(value="/official/shop",method = RequestMethod.POST, produces = "application/json")
	@AccessLimit(seconds=60, maxCount=5, needLogin=true)
	public Result<?> uploadSeller(Model model,FansSession fans, @Valid OfficialSellers OfficialSellers){
		Boolean flag = officialService.saveOfficialShopInfo(fans, OfficialSellers);
		return Result.success(flag);
	}
	
	/**
	 * 官方认证零售户上传
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	@RequestMapping(value="/official/update",method = RequestMethod.GET, produces = "application/json")
	public Result<?> updateOfficialShop(@RequestParam Integer status,@RequestParam Integer shopId){
		Boolean flag = officialService.updateOfficialShopInfo(status, shopId);
		return Result.success(flag);
	}
	
	
	/**
	 * 查询官方认证零售户信息
	 * @param shopId
	 *
	 * @param type  0 烟友分享 1.官方认证
	 */
	@RequestMapping(value="/zyzs/queryByShopId",method = RequestMethod.POST, produces = "application/json")
	@AccessLimit(seconds=60, maxCount=5, needLogin=true)
	public Result<?> uploadSeller(Model model,FansSession fans,@RequestParam Integer shopId,@RequestParam String type){
		if(type.equals("1")){
			OfficialSellers sellers  = officialService.queryByShopId(shopId);
			return Result.success(sellers);
		}
		ZyzsSmokerShareSellers sellers = smokerShareService.queryShopById(shopId);
		return Result.success(sellers);
	}
	
	
	/**
	 * 查询官方认证零售户信息
	 * @param zyzsId
	 * @throws IOException
	 */
	@RequestMapping(value="/official/queryByZyzsId",method = RequestMethod.POST, produces = "application/json")
	@AccessLimit(seconds=60, maxCount=5, needLogin=true)
	public Result<?> uploadSeller(Model model,FansSession fans,@RequestParam String zyzsId){
		OfficialSellers sellers  = officialService.queryByZyzsId(zyzsId);
		return Result.success(sellers);
	}
	
	/**
	 * 查询官方认证零售户信息
	 * @param zyzsId
	 * @throws IOException
	 */
	@RequestMapping(value="/shop/nearby",method = RequestMethod.POST, produces = "application/json")
	@AccessLimit(seconds=60, maxCount=5, needLogin=true)
	public Result<?> queryNearby(Model model,FansSession fans,@Valid QueryNearyVM vm){
		List<ShopVM> res  = officialService.queryNearby(vm);
		return Result.success(res);
	}
	
	
	
	
	
	
}
