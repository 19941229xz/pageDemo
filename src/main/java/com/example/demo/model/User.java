package com.example.demo.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.example.demo.common.base.BaseModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author xiongzh
 * @comment 1，角色实体类 对应数据表 users
 */
@ApiModel(value="user",description="用户表")
@Data
//@EqualsAndHashCode(callSuper=false) //解决继承 BaseModel @Data注解报错的问题
public class User {

	@ApiModelProperty(value="主键 ",name="id")
    private String id;  // 主键 

    @ApiModelProperty(value="账号",name="username")
    @NotEmpty(message="账号不能为空")
    private String username; // 账号
    
    @ApiModelProperty(value="密码",name="password")
    @NotEmpty(message="密码不能为空")
    private String password; // 密码
    
    @ApiModelProperty(value="真实姓名",name="realName")
    @NotEmpty(message="真实姓名不能为空")
    private String realName; // 真实姓名
    
    @ApiModelProperty(value="电话",name="phone")
    @NotEmpty(message="电话不能为空")
    private String phone; // 电话
    
    @ApiModelProperty(value="角色id",name="roleId")
    @NotEmpty(message="角色id不能为空")
    private String roleId; // 角色id
    
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
