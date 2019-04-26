package com.xc.microservice.validate.model.nearby;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 烟友分享的零售户
 * @author zk
 *
 */
public class ShareSellers {
	private Integer id;
	private String shopName;
	private String shopAddress;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private String status;
	private Date createDate;
	private Date updateDate;
	private String zyzsId;
	private String shopImgs;
	private String isDelete;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getZyzsId() {
		return zyzsId;
	}
	public void setZyzsId(String zyzsId) {
		this.zyzsId = zyzsId;
	}
	public String getShopImgs() {
		return shopImgs;
	}
	public void setShopImgs(String shopImgs) {
		this.shopImgs = shopImgs;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	
}
