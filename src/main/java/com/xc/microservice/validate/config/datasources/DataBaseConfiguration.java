package com.xc.microservice.validate.config.datasources;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
/**
 * 数据源注解类
 * @author zk
 *
 */
@Configuration
public class DataBaseConfiguration {
	/**
     * server
     * @return
     */
    @Bean(name = "newDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid.master.new")
    public DataSource serverDataSource(){
        return DataSourceBuilder.create().build();
    }
    /**
     * sd
     * @return
     */
    @Bean(name="sdDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.master.sd")
    public DataSource sdDataSource() {
        return DataSourceBuilder.create().build();
    }
    
    
    /**
     * hlj
     * @return
     */
    @Bean(name="hljDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.master.hlj")
    public DataSource hljDataSource() {
        return DataSourceBuilder.create().build();
    }
}
