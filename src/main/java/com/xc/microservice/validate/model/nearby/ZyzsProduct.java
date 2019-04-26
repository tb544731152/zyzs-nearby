package com.xc.microservice.validate.model.nearby;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 附近产品表
 * @author zk
 *
 */
public class ZyzsProduct {
	
	private Integer id;
	private String name;
	private String type;
	private String ico;
	private String detailImgs;
	private BigDecimal price;
	private Date createDate;
	private String isDelete;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIco() {
		return ico;
	}
	public void setIco(String ico) {
		this.ico = ico;
	}
	
	public String getDetailImgs() {
		return detailImgs;
	}
	public void setDetailImgs(String detailImgs) {
		this.detailImgs = detailImgs;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	
}
