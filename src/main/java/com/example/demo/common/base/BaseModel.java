package com.example.demo.common.base;

import java.util.Date;

import lombok.Data;

/**
 * 所有实体类的父类  抽离出相同的字段
 * @author Administrator
 *
 */
@Data
public class BaseModel {
	
	protected Date createTime;
	
	protected int isDeleted; // 是否被删除  （0表示未删除，1表示已删除）

}
