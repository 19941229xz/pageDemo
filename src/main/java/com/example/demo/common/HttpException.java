package com.example.demo.common;

public class HttpException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7070982171704187395L;
	private String msg;
	private int code;

	public HttpException(HttpCode httpCode) {
		this.msg=httpCode.getMsg();
		this.code=httpCode.getCode();
	}
	
	
	public HttpException() {
		super();
	}

	
	public HttpException setMsg(String msg) {
		this.msg = msg;
		return this;
	}


	public HttpException setCode(HttpCode httpCode) {
		this.code = httpCode.getCode();
		return this;
	}


	public int getCode(){
		return this.code;
	}
	
	public String getMsg(){
		return this.msg;
	}
	
}
