package com.example.demo.model;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 
 * @author xiongzh
 * @comment 用户 对应数据表 user
 */
@ApiModel(value="user",description="用户")
@Data
public class User {
	
        
    @ApiModelProperty(value="主键",name="id")
        private String id; // 主键
    	
        
    @ApiModelProperty(value="账号",name="username")
    @NotEmpty(message="账号不能为空")
	    private String username; // 账号
    	
        
    @ApiModelProperty(value="密码",name="password")
    @NotEmpty(message="密码不能为空")
	    private String password; // 密码
    	
        
    @ApiModelProperty(value="创建时间",name="createTime",hidden=true)
        private Date createTime; // 创建时间
    	
        
    @ApiModelProperty(value="是否被删除（0表示为删除，1表示已删除）",name="isDeleted",hidden=true)
        private Integer isDeleted; // 是否被删除（0表示为删除，1表示已删除）
    	
        
    @ApiModelProperty(value="真实姓名",name="realName")
    @NotEmpty(message="真实姓名不能为空")
	    private String realName; // 真实姓名
    	
        
    @ApiModelProperty(value="电话",name="phone")
    @NotEmpty(message="电话不能为空")
	    private String phone; // 电话
    	
        
    @ApiModelProperty(value="角色id",name="roleId")
    @NotEmpty(message="角色id不能为空")
	    private String roleId; // 角色id
    	
        
    @ApiModelProperty(value="删除时间",name="deleteTime",hidden=true)
        private Date deleteTime; // 删除时间
    	
        
    @ApiModelProperty(value="删除人id",name="deleteUserId",hidden=true)
        private String deleteUserId; // 删除人id
    	
        
    @ApiModelProperty(value="最后一次更新时间",name="updateLastTime",hidden=true)
        private Date updateLastTime; // 最后一次更新时间
    

}
