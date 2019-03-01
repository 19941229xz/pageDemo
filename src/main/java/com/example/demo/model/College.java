package com.example.demo.model;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 
 * @author xiongzh
 * @comment 学校 对应数据表 college
 */
@ApiModel(value="college",description="学校")
@Data
public class College {
	
        
    @ApiModelProperty(value="主键",name="id")
        private String id; // 主键
    	
        
    @ApiModelProperty(value="学校名称@Name",name="collegeName")
    @NotEmpty(message="学校名称@Name不能为空")
	    private String collegeName; // 学校名称@Name
    	
        
    @ApiModelProperty(value="对接人姓名",name="duijierenName")
    @NotEmpty(message="对接人姓名不能为空")
	    private String duijierenName; // 对接人姓名
    	
        
    @ApiModelProperty(value="对接人电话",name="duijierenPhone")
    @NotEmpty(message="对接人电话不能为空")
	    private String duijierenPhone; // 对接人电话
    	
        
    @ApiModelProperty(value="对接人备注",name="duijierenComment")
        private String duijierenComment; // 对接人备注
    	
        
    @ApiModelProperty(value="学校描述",name="comment")
        private String comment; // 学校描述
    	
        
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
