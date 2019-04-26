package com.xc.microservice.validate.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xc.microservice.validate.dao.SmokeShareRepository;
import com.xc.microservice.validate.dao.SmokerShareMapper;
import com.xc.microservice.validate.model.result.CodeMsg;
import com.xc.microservice.validate.model.result.Result;
import com.zyzs.microservice.validate.domain.nearby.ZyzsSmokerShareSellers;
/**
 * 
 * @author dsk_zyzs@aliyun.com
 * 2019年4月17日
 */
@Service
public class SmokerShareService {
	@Autowired
	private SmokerShareMapper shareMapper;
	
	private SmokeShareRepository smokeShareRepository;
	/**
	 * 保存烟友分享的店铺
	 * @param shareSeller
	 * @return
	 */
	public Result<?> saveShareShop(ZyzsSmokerShareSellers shareSeller){
		int n = shareMapper.newSaveShareShop(shareSeller);
		if (n>0) {
			return Result.success(n);
		}else {
			return Result.error(CodeMsg.SAVE_INFO_ERROR);
		}
	}
	/**
	 * 审核店铺
	 * @param shopId
	 * @param stauts
	 * @return
	 */
	public Result<?> examineShop(String shopId,String stauts){
		int n = shareMapper.newExamineShop(shopId, stauts);
		if (stauts.equals("1")) {
			ZyzsSmokerShareSellers sellers = shareMapper.newQuerySellerByShopId(Integer.parseInt(shopId));
			if(sellers.getStatus().equals("0")){
				sellers.setStatus("1");
				double[] locs = {sellers.getLongitude().doubleValue(),sellers.getLatitude().doubleValue()};
				sellers.setLocs(locs );
				smokeShareRepository.save(sellers);
			}
		}
		if (n>0) {
			return Result.success(n);
		}else {
			return Result.error(CodeMsg.SAVE_INFO_ERROR);
		}
	}
	
	public ZyzsSmokerShareSellers queryShopById(Integer shopId){
		return shareMapper.newQuerySellerByShopId(shopId);
	}
	
}
