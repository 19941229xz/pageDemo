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
	
	/**
	 * swagger标题
	 */
	private String swaggerTitle;
	
	/**
	 * swagger联系人名称
	 */
	private String swaggerContactName;
	
	/**
	 * swagger联系网站
	 */
	private String swaggerContactWebUrl;
	
	/**
	 * swagger联系人邮箱
	 */
	private String swaggerContactEmail;
	
	/**
	 * swaggerAPI文档版本
	 */
	private String swaggerVersion;
	
	/**
	 * swagger文档描述
	 */
	private String swaggerDescription;
	
	/**
	 * swagger不可见条款
	 */
	private String swaggerTermsOfServiceUrl;
	
}
