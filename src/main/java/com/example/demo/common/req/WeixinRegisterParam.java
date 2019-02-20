package com.example.demo.common.req;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="微信小程序一键绑定注册参数",description="微信小程序一键绑定注册参数")
public class WeixinRegisterParam {
	
	
	/**
	 * 登陆用户名
	 */
	@NotEmpty(message="注册用户名不能为空")
	@ApiModelProperty(value="用户名",name="username")
	private String username;
	
	/**
	 * 登陆密码
	 */
	@NotEmpty(message="注册密码不能为空")
	@ApiModelProperty(value="密码",name="password")
	private String password;

	
	/**
	 * 小程序自己的 appid
	 */
	@NotEmpty(message="注册时小程序的appid不能为空")
	@ApiModelProperty(value="小程序的appid",name="appid",example="wx7d177a566047c728")
	private String appid;
	
	/**
	 * 用户的 openid
	 */
	@NotEmpty(message="注册时用户的 openid不能为空")
	@ApiModelProperty(value="用户的 openid",name="openid",example="oAjjj5LpSzSecr8y2A-LIViRRkLI")
	private String openid;
	

		
		
}
