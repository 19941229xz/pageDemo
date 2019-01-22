package com.example.demo.model;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 
 * @author xiongzh
 * @comment 菜单 对应数据表 menu
 */
@ApiModel(value="menu",description="菜单")
@Data
public class Menu {
	
        
    @ApiModelProperty(value="主键",name="id")
        private String id; // 主键
    	
        
    @ApiModelProperty(value="菜单名称",name="menuName")
    @NotEmpty(message="菜单名称不能为空")
	    private String menuName; // 菜单名称
    	
        
    @ApiModelProperty(value="菜单的地址或路由",name="url")
    @NotEmpty(message="菜单的地址或路由不能为空")
	    private String url; // 菜单的地址或路由
    	
        
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
