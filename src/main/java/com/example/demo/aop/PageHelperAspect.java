package com.example.demo.aop;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiongzh
 * @date 18/12/18 下午10:38
 */
@Aspect
@Component
@Slf4j
public class PageHelperAspect {

    @Pointcut("execution(public * com.example.demo.service.*.*WithPage(..))")
    public void serviceFindFunction(){}
    /**
     * 使用around方法 在执行查询方法前执行PageHelper.startWith
     * 在执行查询方法后 将结果封装到PageInfo中
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("serviceFindFunction()")
    public Object serviceImplAop(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("进入PageHelper AOP");

        //获取连接点方法运行时的入参列表
        Object[] args = proceedingJoinPoint.getArgs();

        //获取连接点的方法签名对象
        Signature signature = proceedingJoinPoint.getSignature();
        
        //获取连接点所在的类的对象(实例)
        Object target = proceedingJoinPoint.getTarget(); 
        
        PageHelper.startPage(Integer.parseInt(args[0].toString()),Integer.parseInt(args[1].toString()));

        log.info("方法[{}]开始执行...",signature.getName());
        Object object = proceedingJoinPoint.proceed();
        log.info("方法[{}]执行结束.",signature.getName());

        if(object instanceof List) {
            List objList = (List) object;
            PageInfo pageInfo = new PageInfo<>(objList);
            return pageInfo;
        }
        return object;

    }

}
