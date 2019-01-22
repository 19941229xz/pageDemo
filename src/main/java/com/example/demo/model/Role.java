package com.example.demo.model;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 
 * @author xiongzh
 * @comment 角色 对应数据表 role
 */
@ApiModel(value="role",description="角色")
@Data
public class Role {
	
        
    @ApiModelProperty(value="主键",name="id")
        private String id; // 主键
    	
        
    @ApiModelProperty(value="角色名称",name="roleName")
    @NotEmpty(message="角色名称不能为空")
	    private String roleName; // 角色名称
    	
        
    @ApiModelProperty(value="角色英文标志",name="roleSign")
    @NotEmpty(message="角色英文标志不能为空")
	    private String roleSign; // 角色英文标志
    	
        
    @ApiModelProperty(value="删除时间",name="deleteTime",hidden=true)
        private Date deleteTime; // 删除时间
    	
        
    @ApiModelProperty(value="删除人id",name="deleteUserId",hidden=true)
        private String deleteUserId; // 删除人id
    	
        
    @ApiModelProperty(value="最后一次更新时间",name="updateLastTime",hidden=true)
        private Date updateLastTime; // 最后一次更新时间
    	
        
    @ApiModelProperty(value="创建时间",name="createTime",hidden=true)
        private Date createTime; // 创建时间
    	
        
    @ApiModelProperty(value="是否被删除（0表示为删除，1表示已删除）",name="isDeleted",hidden=true)
        private Integer isDeleted; // 是否被删除（0表示为删除，1表示已删除）
    

}
