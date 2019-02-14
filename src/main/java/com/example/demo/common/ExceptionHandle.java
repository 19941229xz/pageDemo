package com.example.demo.common;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import static com.example.demo.common.HttpResponse.*;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionHandle {

	
	@ExceptionHandler(value = Exception.class)
    @ResponseBody
    public HttpResponse handle(Exception e) {
        if (e instanceof HttpException) {   //判断异常是否是我们定义的异常
        	log.info("【自定义服务异常】 错误码：{} 信息：{}", 
        			((HttpException) e).getCode(),((HttpException) e).getMsg());
        	HttpException httpException = (HttpException) e;
        	return error(httpException);
        }else if(e instanceof MethodArgumentNotValidException){
        	MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
        	String msg=exception.getBindingResult().getFieldError().getDefaultMessage();
        	log.info("【参数校验失败】{}", e.getMessage());
        	return error(new HttpException().setCode(HttpCode.BAD_PARAM)
        			.setMsg(msg));
        	
        	
        	
        }else if(e instanceof AuthenticationException){
        	AuthenticationException exception = (AuthenticationException) e;
        	String msg=exception.getMessage();
        	log.info("【身份认证失败】{}", e.getMessage());
        	return error(new HttpException().setCode(HttpCode.AUTH_FAIL)
        			.setMsg(msg));
        	
        	
        }else if(e instanceof UnauthorizedException){
        	UnauthorizedException exception = (UnauthorizedException) e;
        	String msg=exception.getMessage();
        	log.info("【没有访问权限】{}", e.getMessage());
        	return error(new HttpException(HttpCode.ACCESS_DENY));
        	
        	
        }else if(e instanceof HttpRequestMethodNotSupportedException){
        	HttpRequestMethodNotSupportedException exception = (HttpRequestMethodNotSupportedException) e;
        	String msg=exception.getMessage();
        	log.info("【请求方式错误】{}", e.getMessage());
        	return error(new HttpException(HttpCode.REQUEST_METHOD_WRONG));
        	
        	
        }else {
            log.error("【系统未知异常】{}", e.getMessage());
            e.printStackTrace();
            return unknowError();
        }
    }
	
	@ExceptionHandler(value = AuthenticationException.class)
    @ResponseBody
    public HttpResponse handle(AuthenticationException e) {
		String msg=e.getMessage();
    	log.info("【认证失败】{}", e.getMessage());
    	return error(new HttpException().setCode(HttpCode.AUTH_FAIL)
    			.setMsg(msg));
	}
	
}
