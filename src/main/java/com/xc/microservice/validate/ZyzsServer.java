package com.xc.microservice.validate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.xc.microservice.validate.model.vm.WxAuth;


@SpringBootApplication
@EnableTransactionManagement 
@EnableConfigurationProperties(value={WxAuth.class})
public class ZyzsServer{
	
	 public static void main(String[] args) {
		    SpringApplication.run(ZyzsServer.class, args);
     }

	
}
