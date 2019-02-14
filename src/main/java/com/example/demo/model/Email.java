package com.example.demo.model;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 
 * @author xiongzh
 * @comment 邮件 对应数据表 Email
 */
@ApiModel(value="Email",description="邮件")
@Data
public class Email {
	
        
    @ApiModelProperty(value="主键",name="id")
        private String id; // 主键
    	
        
    @ApiModelProperty(value="邮箱地址@Name",name="emailAddress")
    @NotEmpty(message="邮箱地址@Name不能为空")
	    private String emailAddress; // 邮箱地址@Name
    	
        
    @ApiModelProperty(value="邮箱密码",name="password")
    @NotEmpty(message="邮箱密码不能为空")
	    private String password; // 邮箱密码
    	
        
    @ApiModelProperty(value="邮箱描述",name="commet")
    @NotEmpty(message="邮箱描述不能为空")
	    private String commet; // 邮箱描述
    

}
