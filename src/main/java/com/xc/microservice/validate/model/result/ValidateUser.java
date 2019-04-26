package com.xc.microservice.validate.model.result;

import javax.validation.constraints.NotNull;

public class ValidateUser {
	@NotNull
	private String openid;
	@NotNull
	private String zsNum;
	private String longitude;
	private String latitude;
	@NotNull
	private String code;
	private String addressCode;
	private String appId;
	private String token;
	private String ip;
	private String sideId;
	private Integer status;
	private Boolean isValidate;
	
	public String getSideId() {
		return sideId;
	}
	public void setSideId(String sideId) {
		this.sideId = sideId;
	}
	private  String trackId;
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getZsNum() {
		return zsNum;
	}
	public void setZsNum(String zsNum) {
		this.zsNum = zsNum;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getAddressCode() {
		return addressCode;
	}
	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getTrackId() {
		return trackId;
	}
	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Boolean getIsValidate() {
		return isValidate;
	}
	public void setIsValidate(Boolean isValidate) {
		this.isValidate = isValidate;
	}
	
	
}
