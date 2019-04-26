package com.xc.microservice.validate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xc.microservice.validate.dao.ApiInfoRepository;
import com.xc.microservice.validate.model.entity.ApiInfo;



@Transactional(readOnly=false)
@Service
public class ApiInfoService {
	@Autowired
	private ApiInfoRepository apiInfoRepository;
	
	public void deleteAll(){
		apiInfoRepository.deleteAll();
	}
	
	public void saveList(Iterable<ApiInfo>list){
		apiInfoRepository.save(list);
	}
	
	public ApiInfo getByApiName(String apiName){
		return apiInfoRepository.findByName(apiName);
	}
}
