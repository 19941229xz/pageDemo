package com.example.demo.model;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 
 * @author xiongzh
 * @comment 用户（老师或者学生）关联班级 对应数据表 userAndBanji
 */
@ApiModel(value="userAndBanji",description="用户（老师或者学生）关联班级")
@Data
public class UserAndBanji {
	
        
    @ApiModelProperty(value="用户（老师或者学生）id",name="userId")
    @NotEmpty(message="用户（老师或者学生）id不能为空")
	    private String userId; // 用户（老师或者学生）id
    	
        
    @ApiModelProperty(value="班级id",name="banjiId")
    @NotEmpty(message="班级id不能为空")
	    private String banjiId; // 班级id
    	
        
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
