package com.example.demo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="HttpResponse",description="返回结果")
@Data
public class HttpResponse {

	@ApiModelProperty(value="返回状态码",name="code")
	private int code;
	
	@ApiModelProperty(value="返回提示",name="msg")
	private String msg;
	
	@ApiModelProperty(value="返回数据",name="content")
	private Object content;
	
	public static HttpResponse error(HttpException e){
		HttpResponse rsp=new HttpResponse();
		rsp.setCode(e.getCode());
		rsp.setMsg(e.getMsg());
		rsp.setContent(null);
		return rsp;
	}
	
	public static HttpResponse fail(HttpException e,Object content){
		HttpResponse rsp=new HttpResponse();
		rsp.setCode(e.getCode());
		rsp.setMsg(e.getMsg());
		rsp.setContent(content);
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
