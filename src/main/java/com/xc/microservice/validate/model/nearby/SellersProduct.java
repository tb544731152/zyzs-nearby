package com.xc.microservice.validate.model.nearby;

import java.util.Date;

/**
 * 商户产品关联表
 * @author zk
 *
 */
public class SellersProduct {
	
	private Integer id;
	private String zyzsId;
	private Integer pdcId;
	private Date createdate;
	private Date updateDate;
	private String Operator;
	private String isDelete;
	
	
	
	public SellersProduct() {
		super();
	}
	public SellersProduct(String zyzsId, Integer pdcId, String operator) {
		super();
		this.zyzsId = zyzsId;
		this.pdcId = pdcId;
		Operator = operator;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getZyzsId() {
		return zyzsId;
	}
	public void setZyzsId(String zyzsId) {
		this.zyzsId = zyzsId;
	}
	
	public Integer getPdcId() {
		return pdcId;
	}
	public void setPdcId(Integer pdcId) {
		this.pdcId = pdcId;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getOperator() {
		return Operator;
	}
	public void setOperator(String operator) {
		Operator = operator;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	

}
