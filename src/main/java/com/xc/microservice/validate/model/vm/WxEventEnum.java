package com.xc.microservice.validate.model.vm;

public enum WxEventEnum {
	TEXT_MESSAGE_TYPE("text","文本消息"),
	CUSTOMER_SERVICE_TRANSFER("transfer_customer_service","通信服务"),
	MUSIC_MESSAGE_TYPE("music","音乐消息"),
	NEWS_MESSAGE_TYPE("news","图文"),
	IMAGE_MESSAGE_TYPE("image","图片"),
	LOCATION_MESSAGE_TYPE("location","位置"),
	VOICE_MESSAGE_TYPE("location","音频 "),
	EVENT_MESSAGE_TYPE("event","推送 "),
	SUBSCRIBE_EVENT_TYPE("subscribe","订阅 "),
	UNSUBSCRIBE_EVENT_TYPE("unsubscribe","取消订阅 "),
	CLICK_EVENT_TYPE("CLICK","自定义菜单点击事件 "),
	SCAN_EVENT_TYPE("SCAN","扫码 "),
	;
	private String  type;

    private String message;
    
    WxEventEnum(String type, String message) {
        this.type = type;
        this.message = message;
    }
    public String getType() {
		return type;
	}
	
	public String getMessage(){
		return message;
	}
}
