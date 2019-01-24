package com.example.demo.common.req;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="登陆参数",description="登陆参数")
public class LoginParam {

	/**
	 * 登陆用户名
	 */
	@NotEmpty(message="登陆用户名不能为空")
	@ApiModelProperty(value="用户名",name="username")
	private String username;
	
	/**
	 * 登陆密码
	 */
	@NotEmpty(message="登陆密码不能为空")
	@ApiModelProperty(value="密码",name="password")
	private String password;
	

		
		
}
