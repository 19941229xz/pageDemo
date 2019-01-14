package com.example.demo.model;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 
 * @author xiongzh
 * @comment 角色 对应数据表 Role
 */
@ApiModel(value="Role",description="角色")
@Data
public class Role {
	
    @ApiModelProperty(value="主键",name="id")
        private String id; // 主键
    	
    @ApiModelProperty(value="角色名称NotEmpty",name="roleName")
    @NotEmpty(message="角色名称不能为空")
	    private String roleName; // 角色名称
    	
    @ApiModelProperty(value="角色英文标志NotEmpty",name="roleSign")
    @NotEmpty(message="角色英文标志不能为空")
	    private String roleSign; // 角色英文标志
    	
    @ApiModelProperty(value="删除时间",name="deleteTime")
        private Date deleteTime; // 删除时间
    	
    @ApiModelProperty(value="删除人id",name="deleteUserId")
        private String deleteUserId; // 删除人id
    	
    @ApiModelProperty(value="最后一次更新时间",name="updateLastTime")
        private Date updateLastTime; // 最后一次更新时间
    	
    @ApiModelProperty(value="创建时间",name="createTime")
        private Date createTime; // 创建时间
    	
    @ApiModelProperty(value="是否被删除（0表示为删除，1表示已删除）",name="isDeleted")
        private Integer isDeleted; // 是否被删除（0表示为删除，1表示已删除）
    

}
