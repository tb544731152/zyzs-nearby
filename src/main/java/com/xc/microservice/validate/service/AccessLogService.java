package com.xc.microservice.validate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xc.microservice.validate.dao.AccessLogRepository;
import com.xc.microservice.validate.model.entity.AccessLog;


@Service
@Transactional(readOnly=true)
public class AccessLogService {
	
	@Autowired
	private AccessLogRepository accessLogRepository;
	
	@Transactional(readOnly=false)
	public void save(AccessLog accessLog) {
		accessLogRepository.save(accessLog);
	}
}
