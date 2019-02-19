package com.example.demo.common.req;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="微信小程序登陆参数",description="微信小程序登陆参数")
public class WexinLoginParam {

	/**
	 * wx.login  获取到的code
	 */
	@NotEmpty(message="登陆凭证code不能为空")
	@ApiModelProperty(value="登陆凭证",name="code",example="从小程序获取")
	private String code;
	
	/**
	 * 小程序自己的 appid
	 */
	@NotEmpty(message="小程序的appid不能为空")
	@ApiModelProperty(value="小程序的appid",name="appid",example="wx7d177a566047c728")
	private String appid;
	
	/**
	 * 小程序自己的 secret
	 */
	@NotEmpty(message="小程序的secret不能为空")
	@ApiModelProperty(value="小程序的secret",name="secret",example="81fffabade65a397ab9fc2c00beb4637")
	private String secret;
	

		
		
}
