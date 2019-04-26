package com.xc.microservice.validate.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xc.microservice.validate.model.nearby.OfficialSellers;

@Mapper
public interface OfficialShopMapper {
	
	@Insert("INSERT INTO `zyzs_nearby`.`zyzs_offical_sellers` ("
			+ "`name`, `phone`, `shop_name`, `shop_address`,"
			+ " `latitude`, `longitude`, `status`, "
			+ "`create_date`,"
			+ "`zyzsId`, `shop_imgs`, "
			+ "`business_license_num`, `business_license_img`, "
			+ "`thumbs`, `isDelete`)"
			+ " VALUES "
			+ "(#{name},#{phone},#{shopName},#{shopAddress},"
			+ "#{latitude},#{longitude},'0',"
			+ "now(),"
			+ "#{zyzsId},#{shopImgs},#{businessLicenseNum},#{businessLicenseImg}, '0', '0');")
	public int newSaveOfficialShop(OfficialSellers OfficialSellers);
	
	
	@Update("update zyzs_offical_sellers SET `status`='1',update_date = NOW() where id=#{id}")
	public Integer newUpdateOfficialPass(Integer id);
	
	@Update("update zyzs_offical_sellers SET `status`='2',update_date = NOW(),isDelete = '1' where id=#{id}")
	public Integer newUpdateOfficialNoPass(Integer id);
	
	@Select("SELECT * from zyzs_offical_sellers WHERE id=#{shopId} AND isDelete='0'")
	public OfficialSellers newQueryOfficialByShopId(Integer shopId);
	
	@Select("SELECT * from zyzs_offical_sellers WHERE zyzsId=#{zyzsId} AND isDelete='0'")
	public OfficialSellers newQueryOfficialByZyzsId(String zyzsId);
	
	
}
