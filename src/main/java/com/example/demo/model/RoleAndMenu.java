package com.example.demo.model;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 
 * @author xiongzh
 * @comment 角色对应的权限 对应数据表 roleAndMenu
 */
@ApiModel(value="roleAndMenu",description="角色对应的权限")
@Data
public class RoleAndMenu {
	
        
    @ApiModelProperty(value="角色id",name="roleId")
    @NotEmpty(message="角色id不能为空")
	    private String roleId; // 角色id
    	
        
    @ApiModelProperty(value="菜单id",name="menuId")
    @NotEmpty(message="菜单id不能为空")
	    private String menuId; // 菜单id
    	
        
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
