package com.example.demo.common;

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
        	log.info("【参数错误】{}", e.getMessage());
        	return error(new HttpException().setCode(HttpCode.BAD_PARAM)
        			.setMsg(msg));
        }else {
            log.error("【系统未知异常】{}", e.getMessage());
            e.printStackTrace();
            return unknowError();
        }
    }
	
}
