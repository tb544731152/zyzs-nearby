package com.xc.microservice.validate.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xc.microservice.validate.model.entity.FansSession;
import com.xc.microservice.validate.model.nearby.SellersProduct;
import com.xc.microservice.validate.model.nearby.ZyzsProduct;

@Mapper
public interface SkuMapper {
	
	@Select("SELECT b.id,b.name,b.type,b.ico,b.detail_imgs,b.price,b.createDate,b.isDelete "
			+" from  (SELECT pdc_id from  zyzs_sellers_relation_sku WHERE zyzsId = #{zyzzsId} AND is_delete ='0') "
			+ "as a LEFT JOIN zyzs_sellers_sku as b on a.pdc_id = b .id WHERE b.isDelete='0'")
	public List<ZyzsProduct> newQuerySkuByZyzsId(String zyzsId);
	
	
	@Select("SELECT * from zyzs_sellers_sku WHERE isDelete='0'")
	public List<ZyzsProduct> newQuerySkuAll();
	
	@Update("UPDATE zyzs_sellers_relation_sku SET is_delete='1',update_date=now(),operate = #{zyzsId} WHERE zyzsId=#{zyzsId}")
	public Integer newUpdateShopSkuDel(String zyzsId);
	
	@Insert("<script>INSERT INTO `zyzs_nearby`.`zyzs_sellers_relation_sku` "
			+ "(`zyzsId`, `pdc_id`, `create_date`, `operate`, `is_delete`) VALUES  "
			+ "<foreach collection=\"pdcIds\" item=\"pdcId\" separator=\",\"> "
			+"(#{zyzsId},#{pdcId},now(),#{zyzsId},'0')"
			+" 	</foreach> </script>")
	public Integer newInsertShopSkus(@Param("pdcIds")List<Integer> pdcIds,@Param("zyzsId")String zyzsId);
}
