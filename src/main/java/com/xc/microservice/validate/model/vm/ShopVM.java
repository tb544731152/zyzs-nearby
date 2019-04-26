package com.xc.microservice.validate.model.vm;

import java.math.BigInteger;

public class ShopVM {
	private String shopIco;
	
	private String shopName;
	
	private String shopAddress;
	
	private Double dis;
	
	private BigInteger shopId;
	
	private Integer thumbs;
	
	private String type;

	public String getShopIco() {
		return shopIco;
	}

	public void setShopIco(String shopIco) {
		this.shopIco = shopIco;
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

	

	public Double getDis() {
		return dis;
	}

	public void setDis(Double dis) {
		this.dis = dis;
	}

	

	public BigInteger getShopId() {
		return shopId;
	}

	public void setShopId(BigInteger shopId) {
		this.shopId = shopId;
	}

	public Integer getThumbs() {
		return thumbs;
	}

	public void setThumbs(Integer thumbs) {
		this.thumbs = thumbs;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

}
