package com.example.demo.common;

public enum HttpCode {
	
	SUCCESS(10200,"ok")
	,UNKNOW_ERROR(10500,"服务未知异常")
	,BAD_PARAM(10400,"参数错误")
	,ITEM_NOT_FOUND(10404,"数据找不到")
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
