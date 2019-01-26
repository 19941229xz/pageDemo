package com.example.demo.aop;

import java.lang.reflect.Method;
import java.util.Date;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static org.springframework.util.StringUtils.*;

import com.example.demo.common.HttpCode;
import com.example.demo.common.HttpException;
import com.example.demo.common.auth.JwtService;
import com.example.demo.common.base.BaseReqParam;
import com.example.demo.model.Role;

import static com.example.demo.common.util.AopUtil.*;
import static com.example.demo.common.util.UUIDUtil.*;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiongzh
 * @date 18/12/18 下午10:38
 */
@Aspect
@Component
@Slf4j
public class ControllerAddAspect {
	
	@Autowired
	JwtService jwtService;
	
	// 拦截 controller addOne 所有方法
    @Pointcut("execution(public * com.example.demo.controller.*.addOne(..))")
    public void addFindFunction(){}
    
    // 拦截 controller addSome 所有方法
    @Pointcut("execution(public * com.example.demo.controller.*.addSome(..))")
    public void addSomeFindFunction(){}
    
    
    
    
    /**
     * 处理controller 中的 add param
     * @throws Throwable 
     */
    @Around("addFindFunction()")
    public Object processAddParam(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    	log.debug("进入ControllerAddAspect AOP");
    	

//    	System.out.println("this user role id is"+jwtService.getUserInfo().getRoleId());
    	
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
        
        // 获取addParam
        Object obj= param.getAddParam();
        
        //添加role时 需要前端生成id  例如：SYS_ADMIN 如果没传id 需要提示
        if((obj instanceof Role)) {
        	//判断id不为空
        	if(isEmpty(getFieldValue(obj, "id"))) {
        		throw new HttpException(HttpCode.BAD_PARAM).setMsg("添加role时 需要前端生成id  例如：SYS_ADMIN");
        	}
        	//只有系统管理员 有添加角色的权限
        	if(!jwtService.getUserInfo().getRoleId().equals("SYS_ADMIN")) {
        		throw new HttpException(HttpCode.ACCESS_DENY).setMsg("您没有添加角色的权限");
        	}
        	
        }
        // add param  ieDeleted  must be 0 
        setFieldValue(obj,"isDeleted",0);
        // add param need auto-generate a id
        setFieldValue(obj,"id",uuid());
        // add param generate a createTime
        setFieldValue(obj, "createTime", new Date());
        // add paran update the lastupdatetime
        setFieldValue(obj, "updateLastTime", new Date());
        // delete time and delete user id must be null
        setFieldValue(obj, "deleteTime", null);
        setFieldValue(obj, "deleteUserId", null);
        
        Object object = proceedingJoinPoint.proceed();
        return object;
    }
    
    	
    

}
