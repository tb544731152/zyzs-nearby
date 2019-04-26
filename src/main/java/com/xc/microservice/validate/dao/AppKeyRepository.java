package com.xc.microservice.validate.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xc.microservice.validate.model.entity.AppKey;

/**
 * appkey管理操作
 * @author zk
 *
 */
public interface AppKeyRepository extends MongoRepository<AppKey, String> {
	
	AppKey findByAppId(String appId);
}
