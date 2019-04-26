package com.xc.microservice.validate.model.vm;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "wxapp")
@Data
public class WxAuth {
	private String projectUrl;
	
	private String sideId;
	
	private String appId;
	
	private String secret;
	
	private String grantType;
	
	private String sessionHost;
	
}
