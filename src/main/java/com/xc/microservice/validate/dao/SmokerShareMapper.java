package com.xc.microservice.validate.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zyzs.microservice.validate.domain.nearby.ZyzsSmokerShareSellers;

@Mapper
public interface SmokerShareMapper {
	//保存烟友分享店铺
	@Insert("INSERT INTO zyzs_smoker_share_sellers(`shopName`, `shopAddress`, `latitude`, "
			+ "`longitude`, `shop_imgs`, `status`, `zyzsId`, `createDate`, `updateDate`, "
			+ "`isDelete`, `thumbs`) VALUES (#{shareSeller.shopName},#{shareSeller.shopAddress},"
			+ "#{shareSeller.latitude},#{shareSeller.longitude},#{shareSeller.shop_imgs},"
			+ "#{shareSeller.status},#{shareSeller.zyzsId},#{shareSeller.createDate},"
			+ "#{shareSeller.updateDate},#{shareSeller.isDelete},#{shareSeller.thumbs});")
	public int newSaveShareShop(@Param("shareSeller")ZyzsSmokerShareSellers shareSeller);
	
	//审核店铺 修改状态
	@Update("UPDATE zyzs_smoker_share_sellers SET `status`=#{stauts} WHERE id=#{shopId}")
	public int newExamineShop(@Param("shopId") String shopId,@Param("stauts") String stauts);
	
	@Select("SELECT * from zyzs_smoker_share_sellers WHERE id=#{shopId}")
	public ZyzsSmokerShareSellers newQuerySellerByShopId(Integer shopId);
}
