package com.xc.microservice.validate.model.nearby;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;



/**
 * 中烟追溯官方认证零售户
 * @author zk
 *
 */
public class OfficialSellers {
	private BigInteger id;
	private String name;
	private String phone;
	private String shopName;
	private String shopAddress;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private double[] locs = new double[2];
	private String status;
	private Date createDate;
	private Date updateDate;
	private String zyzsId;
	private String shopImgs;
	private String businessLicenseNum;
	private String businessLicenseImg;
	private Integer thumbs;
	private String isDelete;
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getBusinessLicenseNum() {
		return businessLicenseNum;
	}
	public void setBusinessLicenseNum(String businessLicenseNum) {
		this.businessLicenseNum = businessLicenseNum;
	}
	public String getBusinessLicenseImg() {
		return businessLicenseImg;
	}
	public void setBusinessLicenseImg(String businessLicenseImg) {
		this.businessLicenseImg = businessLicenseImg;
	}
	public Integer getThumbs() {
		return thumbs;
	}
	public void setThumbs(Integer thumbs) {
		this.thumbs = thumbs;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public double[] getLocs() {
		return locs;
	}
	public void setLocs(double[] locs) {
		this.locs = locs;
	}
	
	
	

	
}
