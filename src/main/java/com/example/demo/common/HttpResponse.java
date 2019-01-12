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
		rsp.setCode(500);
		rsp.setMsg("服务异常");
		rsp.setContent(null);
		return rsp;
	}
	
	public static HttpResponse success(Object content){
		HttpResponse rsp=new HttpResponse();
		rsp.setCode(200);
		rsp.setMsg("ok");
		rsp.setContent(content);
		return rsp;
	}
	
}
