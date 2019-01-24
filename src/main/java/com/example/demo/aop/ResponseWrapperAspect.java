package com.example.demo.aop;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.common.HttpResponse;
import com.example.demo.common.SystemConfig;
import com.example.demo.common.base.BaseReqParam;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiongzh
 * @date 18/12/18 下午10:38
 */
@Aspect
@Component
@Slf4j
public class ResponseWrapperAspect {
	
	@Autowired
	private SystemConfig systemConfig;
	
	// 拦截 controller 所有方法
    @Pointcut("execution(public * com.example.demo.controller.*.**(..))")
    public void serviceFindFunction(){}
    /**
     * 
     */
    @Around("serviceFindFunction()")
    public Object serviceImplAop(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.debug("进入ResponseWrapperAspect AOP");
        //获取连接点方法运行时的入参列表
        Object[] args = proceedingJoinPoint.getArgs();
        //获取连接点的方法签名对象
        Signature signature = proceedingJoinPoint.getSignature();
        //获取连接点所在的类的对象(实例)
        Object target = proceedingJoinPoint.getTarget(); 
        //对象转换
//        BaseReqParam<Object> param=(BaseReqParam<Object>)args[0];
        
        Object object = proceedingJoinPoint.proceed();

        if(!(object instanceof HttpResponse)) {
            return HttpResponse.success(object);
        }
        return object;

    }
    

}
