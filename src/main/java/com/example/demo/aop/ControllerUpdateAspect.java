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

import com.example.demo.common.SystemConfig;
import com.example.demo.common.annotation.IdNotEmpty;
import com.example.demo.common.annotation.IsDeletedSetNull;
import com.example.demo.common.base.BaseReqParam;
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
public class ControllerUpdateAspect {
	
	@Autowired
	private SystemConfig systemConfig;
	
	// 拦截 controller update方法
    @Pointcut("execution(public * com.example.demo.controller.*.update(..))")
    public void addFindFunction(){}
    
    
    
    
    
    /**
     * 处理controller 中的 add param
     * @throws Throwable 
     */
    @Around("addFindFunction()")
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
        Object obj= param.getUpdateParam();
        setFieldValue(obj,"isDeleted",null);
        // add paran update the lastupdatetime
        setFieldValue(obj, "updateLastTime", new Date());
        // delete time and delete user id must be null
        setFieldValue(obj, "deleteTime", null);
        setFieldValue(obj, "deleteUserId", null);
        // update not allowed change createTime so must be null
        setFieldValue(obj, "createTime", null);
        
        Object object = proceedingJoinPoint.proceed();
        
       
        
        return object;
    }
    
    	
    	
    
    
    

}
