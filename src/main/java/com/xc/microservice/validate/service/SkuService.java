package com.xc.microservice.validate.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xc.microservice.validate.dao.SkuMapper;
import com.xc.microservice.validate.model.nearby.SellersProduct;
import com.xc.microservice.validate.model.nearby.ZyzsProduct;


/**
 * 
 * @author zk
 * 2019年4月17日
 */
@Service
@Transactional
public class SkuService {
	@Autowired
	private SkuMapper skuDao;
	/**
	 * 查询店铺商品
	 * @param zyzsId
	 * @return
	 */
	public List<ZyzsProduct> queryShopSku(String zyzsId){
		return skuDao.newQuerySkuByZyzsId(zyzsId);
	}
	
	/**
	 * 查询所有产品
	 * @return
	 */
	public List<ZyzsProduct> querySku(){
		return skuDao.newQuerySkuAll();
	}
	
	public boolean updateSku(String zyzsId,String [] pdcIds){
		//1.先删除之前所有产品
		skuDao.newUpdateShopSkuDel(zyzsId);
		List<Integer> relations = new ArrayList<Integer>();
		for(String pdcId : pdcIds){
			relations.add(Integer.parseInt(pdcId));
		}
		Integer nums = skuDao.newInsertShopSkus(relations,zyzsId);
		if(nums > 0){
			return true;
		}
		return false;
	}
	
	
}
