package com.example.demo.model;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 
 * @author xiongzh
 * @comment 班级 对应数据表 banji
 */
@ApiModel(value="banji",description="班级")
@Data
public class Banji {
	
        
    @ApiModelProperty(value="主键",name="id")
        private String id; // 主键
    	
        
    @ApiModelProperty(value="班级名称@Name",name="banjiName")
    @NotEmpty(message="班级名称@Name不能为空")
	    private String banjiName; // 班级名称@Name
    	
        
    @ApiModelProperty(value="所属大学id",name="collegeId")
    @NotEmpty(message="所属大学id不能为空")
	    private String collegeId; // 所属大学id
    	
        
    @ApiModelProperty(value="进班邀请码",name="activeCode")
    @NotEmpty(message="进班邀请码不能为空")
	    private String activeCode; // 进班邀请码
    	
        
    @ApiModelProperty(value="班级描述",name="commet")
        private String commet; // 班级描述
    	
        
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
