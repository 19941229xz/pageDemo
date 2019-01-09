package com.example.demo.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import static com.example.demo.common.HttpResponse.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionHandle {

	
	@ExceptionHandler(value = Exception.class)
    @ResponseBody
    public HttpResponse handle(Exception e) {
        if (e instanceof HttpException) {   //判断异常是否是我们定义的异常
        	log.error("【自定义服务异常】{}", e);
        	HttpException httpException = (HttpException) e;
        	return error(httpException);
        }else {
            log.error("【系统未知异常】{}", e);
            return unknowError();
        }
    }
	
}
