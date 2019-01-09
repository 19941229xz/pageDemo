package com.example.demo.common.base;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 所有实体类的父类  抽离出相同的字段
 * @author Administrator
 *
 */
@Data
public class BaseModel {
	
	@ApiModelProperty(value="创建时间",name="createTime")
	protected Date createTime; // 创建时间
	
	@ApiModelProperty(value="是否被删除  （0表示未删除，1表示已删除）",name="isDeleted",hidden=true)
	protected int isDeleted; // 是否被删除  （0表示未删除，1表示已删除）
	
	@ApiModelProperty(value="删除时间",name="deleteTime")
	protected Date deleteTime; // 删除时间
	
	@ApiModelProperty(value="删除人的id",name="deleteUserId")
	protected String deleteUserId; // 删除人的id
	
	@ApiModelProperty(value="最后一次修改的时间",name="updateLastTime")
	protected Date updateLastTime; // 最后一次修改的时间

}
