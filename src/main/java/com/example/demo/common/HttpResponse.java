package com.example.demo.common;

import lombok.Data;

@Data
public class HttpResponse {

	private int code;
	
	private String msg;
	
	private Object content;
	
	public static HttpResponse error(HttpException e){
		HttpResponse rsp=new HttpResponse();
		rsp.setCode(e.getCode());
		rsp.setMsg(e.getMsg());
		rsp.setContent(null);
		return rsp;
	}
	
	public static HttpResponse unknowError(){
		HttpResponse rsp=new HttpResponse();
		rsp.setCode(HttpCode.UNKNOW_ERROR.getCode());
		rsp.setMsg(HttpCode.UNKNOW_ERROR.getMsg());
		rsp.setContent(null);
		return rsp;
	}
	
	public static HttpResponse success(Object content){
		HttpResponse rsp=new HttpResponse();
		rsp.setCode(HttpCode.SUCCESS.getCode());
		rsp.setMsg(HttpCode.SUCCESS.getMsg());
		rsp.setContent(content);
		return rsp;
	}
	
}
