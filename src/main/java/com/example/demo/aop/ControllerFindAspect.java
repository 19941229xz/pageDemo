package com.example.demo.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.common.HttpCode;
import com.example.demo.common.HttpException;
import com.example.demo.common.HttpResponse;
import com.example.demo.common.SystemConfig;
import com.example.demo.common.base.BaseReqParam;

import static com.example.demo.common.util.AopUtil.*;


import lombok.extern.slf4j.Slf4j;

/**
 * @author xiongzh
 * @date 18/12/18 下午10:38
 */
@Aspect
@Component
@Slf4j
public class ControllerFindAspect {
	
	// 拦截 controller search方法
    @Pointcut("execution(public * com.example.demo.controller.*.search(..))")
    public void searchFindFunction(){}
    
    // 拦截 controller getOne方法
    @Pointcut("execution(public * com.example.demo.controller.*.getOne(..))")
    public void getOneFindFunction(){}
    
    // ||表示同时拦截两个切点
    @Pointcut("searchFindFunction() || getOneFindFunction()")
    private void finalPoint(){}
    
    
    /**
     * 处理controller 中的 search param
     * @throws Throwable 
     */
    @Around("finalPoint()")
    public Object processAddParam(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    	log.debug("进入ControllerFindAspect AOP");

        //获取连接点方法运行时的入参列表
        Object[] args = proceedingJoinPoint.getArgs();

        //获取连接点的方法签名对象 和被拦截的方法
        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;  
        
        //获取连接点所在的类的对象(实例)
        Object target = proceedingJoinPoint.getTarget(); 
        
        //对象转换
        BaseReqParam<Object> param=(BaseReqParam<Object>)args[0];
        
        // add param  ieDeleted  must be null（update not allow change isDeleted） 
        Object obj= param.getSearchParam();
        setFieldValue(obj,"isDeleted",0);
        
        Object object = proceedingJoinPoint.proceed();
        
        // TODO this code need fix to more simple
       if(object == null) { // 被拦截的查询方法返回null  返回自定义异常 ITEM_NOT_FOUND
    	   throw new HttpException(HttpCode.ITEM_NOT_FOUND);
       }else {
    	   if(object instanceof HttpResponse) {
    		   HttpResponse rsp= (HttpResponse)object ;
    		   if(rsp.getContent()==null) { // 如果rsp 内容为空 也返回自定义异常 ITEM_NOT_FOUND
    			   throw new HttpException(HttpCode.ITEM_NOT_FOUND);
    		   }
    	   }
       }
        
        return object;
    }
    

}
