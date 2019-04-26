package com.xc.microservice.validate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.Sphere;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;
import com.xc.microservice.validate.dao.OfficialShopMapper;
import com.xc.microservice.validate.dao.OfficialShopRepository;
import com.xc.microservice.validate.model.entity.FansSession;
import com.xc.microservice.validate.model.nearby.OfficialSellers;
import com.xc.microservice.validate.model.vm.QueryNearyVM;
import com.xc.microservice.validate.model.vm.ShopVM;
import com.xc.microservice.validate.util.LocationUtils;
import com.zyzs.microservice.validate.domain.nearby.ZyzsSmokerShareSellers;
import com.zyzs.microservice.validate.domain.user.ZyzsFans;

/**
 * 
 * @author zk
 * 2019年4月17日
 */
@Service
public class OfficialService {
	
	private static final  double radius = 2000d;
	
	@Autowired
	private OfficialShopMapper officialShopMapper;
	
	@Autowired
	private OfficialShopRepository officialShopRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	/**
	 * 保存官方认证零售户
	 * @param shareSeller
	 * @return
	 */
	public boolean saveOfficialShopInfo(FansSession fans,OfficialSellers OfficialSellers){
		OfficialSellers.setZyzsId(fans.getZyzsId());
		Integer num = officialShopMapper.newSaveOfficialShop(OfficialSellers);
		if(num>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 审核通过  保存至mongdb
	 * @param shareSeller
	 * @return
	 */
	public boolean updateOfficialShopInfo(Integer status,Integer id){
		 if(status.equals(1)){
			 OfficialSellers officalSellers =  officialShopMapper.newQueryOfficialByShopId(id);
			 //保存入monogdb
			 if(officalSellers.getStatus().equals("0")){
				 officialShopMapper.newUpdateOfficialPass(id);
				 officalSellers.setStatus("1");
				 double[] locs = {officalSellers.getLongitude().doubleValue(),officalSellers.getLatitude().doubleValue()};
				 officalSellers.setLocs(locs );
				 officialShopRepository.save(officalSellers);
			 }
		 }else{
			 officialShopMapper.newUpdateOfficialNoPass(id);
		 }
		 return true;
	}
	
	/**
	 * 查询店铺详细信息
	 * @param shopId
	 * @return
	 */
	public OfficialSellers queryByShopId(Integer shopId){
		return  officialShopMapper.newQueryOfficialByShopId(shopId);
	}
	
	/**
	 * 查询店铺详细信息 ZYZSiD
	 * @param zyzsId
	 * @return
	 */
	public OfficialSellers queryByZyzsId(String zyzsId){
		return  officialShopMapper.newQueryOfficialByZyzsId(zyzsId);
	}
	
	
	public List<ShopVM> queryNearby(QueryNearyVM vm){
		return queryOfficial(vm);
	}
	
	public List<ShopVM> queryOfficial(QueryNearyVM vm){
		//double x = 110, y = 38;
		double x = vm.getLongitude().doubleValue(), y = vm.getLatitude().doubleValue();
		Point point = new Point(x, y);
		//6.2137119 / 3963.2为10公里范围，6.2137119是英里=1公里
		Sphere sphere = new Sphere(point, 0.62137119 / 3963.2);
		//查询官方认证
		List<OfficialSellers> positions = mongoTemplate.find(new Query(Criteria.where("locs").within(sphere)), OfficialSellers.class);
		List<ShopVM> shopVMs = new ArrayList<ShopVM>();
		for (OfficialSellers sellers : positions) {
			ShopVM shopvm = new ShopVM();
			shopvm.setShopAddress(sellers.getShopAddress());
			shopvm.setShopIco(sellers.getShopImgs());
			shopvm.setShopId(sellers.getId());
			shopvm.setShopName(sellers.getShopName());
			shopvm.setThumbs(sellers.getThumbs());
			double x2 = sellers.getLongitude().doubleValue(), y2 = sellers.getLatitude().doubleValue();
			shopvm.setType("1");
			shopvm.setDis(LocationUtils.getDistance(y, x, y2, x2));
			shopVMs.add(shopvm);
		}
		//查询烟友分享
		List<ZyzsSmokerShareSellers> positions2 = mongoTemplate.find(new Query(Criteria.where("locs").within(sphere)), ZyzsSmokerShareSellers.class);
		for (ZyzsSmokerShareSellers sellers : positions2) {
			ShopVM shopvm = new ShopVM();
			shopvm.setShopAddress(sellers.getShopAddress());
			shopvm.setShopIco(sellers.getShop_imgs());
			shopvm.setShopId(sellers.getId());
			shopvm.setShopName(sellers.getShopName());
			shopvm.setThumbs(sellers.getThumbs());
			double x2 = sellers.getLongitude().doubleValue(), y2 = sellers.getLatitude().doubleValue();
			shopvm.setType("0");
			shopvm.setDis(LocationUtils.getDistance(y, x, y2, x2));
			shopVMs.add(shopvm);
		}
		return shopVMs;
	}
	
	
}
