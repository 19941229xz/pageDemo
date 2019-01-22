package com.example.demo.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix="systemconfig")
@Data
public class SystemConfig {
	
	/**
	 * 默认分页 pagesize
	 */
	private int defaultPageSize;
	
	/**
	 * token有效时间
	 */
	private long tokeExpireTime;
	
}
