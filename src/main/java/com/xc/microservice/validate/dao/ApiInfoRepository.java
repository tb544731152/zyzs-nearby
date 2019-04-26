package com.xc.microservice.validate.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xc.microservice.validate.model.entity.ApiInfo;

/**
 * api管理操作
 * @author zk
 *
 */
public interface ApiInfoRepository extends MongoRepository<ApiInfo, String> {

	ApiInfo findByName(String apiName);

}
