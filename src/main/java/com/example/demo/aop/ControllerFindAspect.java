package com.example.demo.aop;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.example.demo.common.HttpCode;
import com.example.demo.common.HttpException;
import com.example.demo.common.HttpResponse;
import com.example.demo.common.SystemConfig;
import com.example.demo.common.annotation.IdNotEmpty;
import com.example.demo.common.annotation.IsDeletedSetNull;
import com.example.demo.common.base.BaseReqParam;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers.IntArraySerializer;

import static com.example.demo.common.util.AopUtil.*;

import static com.example.demo.common.util.UUIDUtil.*;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.Field;

/**
 * @author xiongzh
 * @date 18/12/18 下午10:38
 */
@Aspect
@Component
@Slf4j
public class ControllerFindAspect {
	
	@Autowired
	private SystemConfig systemConfig;
	
	// 拦截 controller search方法
    @Pointcut("execution(public * com.example.demo.controller.*.search(..))")
    public void searchFindFunction(){}
    
    // 拦截 controller getOne方法
    @Pointcut("execution(public * com.example.demo.controller.*.getOne(..))")
    public void getOneFindFunction(){}
    
    
    @Pointcut("searchFindFunction() || getOneFindFunction()")
    private void finalPoint(){}
    
    
    /**
     * 处理controller 中的 add param
     * @throws Throwable 
     */
    @Around("finalPoint()")
    public Object processAddParam(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    	log.debug("进入ControllerUpdateAspect AOP");

        //获取连接点方法运行时的入参列表
        Object[] args = proceedingJoinPoint.getArgs();

        //获取连接点的方法签名对象 和被拦截的方法
        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;  
        Method method = methodSignature.getMethod();
        
        //获取连接点所在的类的对象(实例)
        Object target = proceedingJoinPoint.getTarget(); 
        
        //对象转换
        BaseReqParam<Object> param=(BaseReqParam<Object>)args[0];
        
        // add param  ieDeleted  must be null（update not allow change isDeleted） 
        Object obj= param.getSearchParam();
        setFieldValue(obj,"isDeleted",0);
        
        Object object = proceedingJoinPoint.proceed();
        
        // TODO this code need fix to more simple
       if(object == null) {
    	   throw new HttpException(HttpCode.ITEM_NOT_FOUND);
       }else {
    	   if(object instanceof HttpResponse) {
    		   HttpResponse rsp= (HttpResponse)object ;
    		   if(rsp.getContent()==null) {
    			   throw new HttpException(HttpCode.ITEM_NOT_FOUND);
    		   }
    	   }
       }
        
        return object;
    }
    
    	
    	
    
    
    

}
