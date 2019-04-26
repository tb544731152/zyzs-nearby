package com.xc.microservice.validate.model.result;

public class WxSendMsg {
	
	public final static String  templateId = "VEoNroIRZVJZHYLGPXkNZRkWDZuEhdxxwAq7svfa-ik";

	private String openId;
	private String title;
	private String result;
	private String date;
	private String form_id;
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getForm_id() {
		return form_id;
	}
	public void setForm_id(String form_id) {
		this.form_id = form_id;
	}
	
	
}
