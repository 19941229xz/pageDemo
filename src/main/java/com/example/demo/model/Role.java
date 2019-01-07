package com.example.demo.model;

import com.example.demo.common.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author xiongzh
 * @comment 1，角色实体类 对应数据表 roles
 */
@Data
@EqualsAndHashCode(callSuper=false) //解决继承 BaseModel @Data注解报错的问题
public class Role extends BaseModel{
	
	
	private String id;  // 主键 

    private String roleName; // 角色名称
    
    private String roleSign; // 角色标志  （例如：超级管理员为 SYS_ADMIN）

}
