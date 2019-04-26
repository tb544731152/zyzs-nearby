package com.xc.microservice.validate.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.zyzs.microservice.validate.domain.nearby.ZyzsSmokerShareSellers;

/**
 * 烟友分析
 * @author zk
 *
 */
public interface SmokeShareRepository extends MongoRepository<ZyzsSmokerShareSellers, String> {

	ZyzsSmokerShareSellers findById(Integer id);

}
