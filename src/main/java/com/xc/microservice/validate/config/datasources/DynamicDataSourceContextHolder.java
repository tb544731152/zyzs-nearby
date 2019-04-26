package com.xc.microservice.validate.config.datasources;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicDataSourceContextHolder {

		private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);
		   
		private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<String>();
	
		/**
		 * 数据源集合
		 */
		public static List<Object> dataSourceKeys = new ArrayList<Object>();
		/**
		 * 配置数据源方法
		 * @param key
		 */
		public static void setDataSourceKey(String key) {
		        CONTEXT_HOLDER.set(key);
		}
	
		/**
		 * new数据库
		 */
		public static void useNewDataSource() {
		        CONTEXT_HOLDER.set(DataSourceKey.newDataSource.name());
		}
		/**
		 * sd 数据源
		 */
		 public static void useSdDataSource() {
		        CONTEXT_HOLDER.set(DataSourceKey.sdDataSource.name());
		 }
		
		 /**
			 * sd 数据源
			 */
			 public static void useHljDataSource() {
			        CONTEXT_HOLDER.set(DataSourceKey.hljDataSource.name());
			 }		 
	    /**
	     * 获取数据源
	     *
	     * @return data source key
	     */
	    public static String getDataSourceKey() {
	        return CONTEXT_HOLDER.get();
	    }
	    /**
	     * To set DataSource as default
	     */
	    public static void clearDataSourceKey() {
	        CONTEXT_HOLDER.remove();
	    }
	    /**
	     * Check if give DataSource is in current DataSource list
	     *
	     * @param key the key
	     * @return boolean boolean
	     */
	    public static boolean containDataSourceKey(String key) {
	        return dataSourceKeys.contains(key);
	    }
}
