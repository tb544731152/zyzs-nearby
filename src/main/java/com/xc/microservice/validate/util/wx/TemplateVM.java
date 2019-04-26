package com.xc.microservice.validate.util.wx;


/**
 * 三个key
 * @author liuyang@ooyanjing.com
 *
 */
public class TemplateVM {

	private String title;
	private String keyword1;
	private String keyword2;
	private String keyword3;
	private String keyword4;
	private String remark;
	private String url;
	
	
	
	
	public TemplateVM(String title, String keyword1, String keyword2,
			String keyword3, String remark, String url) {
		super();
		this.title = title;
		this.keyword1 = keyword1;
		this.keyword2 = keyword2;
		this.keyword3 = keyword3;
		this.remark = remark;
		this.url = url;
	}
	public TemplateVM() {
		super();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeyword1() {
		return keyword1;
	}
	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}
	public String getKeyword2() {
		return keyword2;
	}
	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}
	public String getKeyword3() {
		return keyword3;
	}
	public void setKeyword3(String keyword3) {
		this.keyword3 = keyword3;
	}
	public String getKeyword4() {
		return keyword4;
	}
	public void setKeyword4(String keyword4) {
		this.keyword4 = keyword4;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
