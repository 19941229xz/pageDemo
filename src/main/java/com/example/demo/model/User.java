package com.example.demo.model;

import javax.validation.constraints.NotEmpty;

import com.example.demo.common.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author xiongzh
 * @comment 1，角色实体类 对应数据表 users
 */
@Data
@EqualsAndHashCode(callSuper=false) //解决继承 BaseModel @Data注解报错的问题
public class User extends BaseModel{

	
    private String id;  // 主键 

    @NotEmpty(message="账号不能为空")
    private String username; // 账号
    
    @NotEmpty(message="密码不能为空")
    private String password; // 密码
    
    @NotEmpty(message="真实姓名不能为空")
    private String realName; // 真实姓名
    
    @NotEmpty(message="电话不能为空")
    private String phone; // 电话
    
    @NotEmpty(message="角色id不能为空")
    private String roleId; // 角色id
    
    
    
    
}
