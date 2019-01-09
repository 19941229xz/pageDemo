package com.example.demo.common;

public enum HttpCode {
	
	SUCCESS(200,"ok")
	,BAD_PARAM(400,"参数错误")
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
