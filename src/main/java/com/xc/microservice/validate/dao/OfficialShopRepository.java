package com.xc.microservice.validate.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.xc.microservice.validate.model.nearby.OfficialSellers;

/**
 * api管理操作
 * @author zk
 *
 */
public interface OfficialShopRepository extends MongoRepository<OfficialSellers, String> {

	
	OfficialSellers findByZyzsId(String zyzsId);

}
