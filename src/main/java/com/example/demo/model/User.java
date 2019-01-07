package com.example.demo.model;

import com.example.demo.common.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false) //解决继承 BaseModel @Data注解报错的问题
public class User extends BaseModel{

	
    private String id;  // 主键 

    private String username; // 账号

    private String password; // 密码
    
    private String realName; // 真实姓名
    
    private String phone; // 电话
    
    private String roleId; // 角色id
    
    
    
    
}
