package com.example.demo.common.req;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="学生微信注册参数",description="学生微信注册参数")
public class StudentRegParam {
	
	
	/**
	 * 学号
	 */
	@NotEmpty(message="学号不能为空")
	@ApiModelProperty(value="学号",name="studentId")
	private String username;
	
	/**
	 * qq号
	 */
	@NotEmpty(message="qq不能为空")
	@ApiModelProperty(value="qq",name="qq")
	private String qq;

	
	/**
	 * wechat
	 */
	@NotEmpty(message="微信号不能为空")
	@ApiModelProperty(value="微信号",name="wechat",example="测试微信号")
	private String wechat;
	
	/**
	 * 手机号
	 */
	@NotEmpty(message="手机号不能为空")
	@ApiModelProperty(value="手机号",name="phone",example="13657211670")
	private String phone;
	
	/**
	 * 性别
	 */
	@NotEmpty(message="性别不能为空")
	@ApiModelProperty(value="性别",name="sex",example="男")
	private String sex;
	
	/**
	 * 班级邀请码
	 */
	@NotEmpty(message="邀请码不能为空")
	@ApiModelProperty(value="邀请码",name="activeCode",example="123321")
	private String activeCode;
	
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
