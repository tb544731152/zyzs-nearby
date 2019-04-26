package com.xc.microservice.validate.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xc.microservice.validate.model.entity.AccessLog;

/**
 * 访问日志操作
 * @author zk
 *
 */
public interface AccessLogRepository extends MongoRepository<AccessLog, String> {

}
