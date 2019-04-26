package com.xc.microservice.validate.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * API访问日志
 * @author zk
 *
 */
@Document(collection="t_access_logs")
@Data
@CompoundIndexes({
    @CompoundIndex(def = "{'accessDate': -1}")
})
@NoArgsConstructor
@AllArgsConstructor
public class AccessLog {
	@Id
	private String id;
	/*api名称*/	
	private String apiName;
	/*接口路径*/
	private String uri;
	/*访问时间*/	
	private String accessDate;
	

	public AccessLog(String apiName, String uri, String accessDate) {
		super();
		this.apiName = apiName;
		this.uri = uri;
		this.accessDate = accessDate;
	}
	
}
