package com.example.demo.common;

public enum HttpCode {
	
	SUCCESS(10200,"ok")
	,UNKNOW_ERROR(10500,"服务未知异常")
	,BAD_PARAM(10400,"参数错误")
	,ITEM_NOT_FOUND(10404,"数据找不到")
	,NULL_TOKEN(10501,"token为空")
	,AUTH_FAIL(10502,"认证失败")
	,LOGIN_FAIL(10503,"登陆失败")
	,NAME_REGISTERED(10504,"名称或账号已经被注册")
	,ACCESS_DENY(10401,"没有访问权限")
	;

	private String msg;
	
	private int code;
	
	
	private HttpCode(int code,String msg) {  
        this.msg = msg;  
        this.code = code;  
    }
	
	public int getCode(){
		return this.code;
	}
	
	public String getMsg(){
		return this.msg;
	}
}
