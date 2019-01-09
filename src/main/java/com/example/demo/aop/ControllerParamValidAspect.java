package com.example.demo.aop;

import java.lang.reflect.Method;
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
public class ControllerParamValidAspect {
	
	@Autowired
	private SystemConfig systemConfig;
	
	// 拦截 controller 所有方法
    @Pointcut("execution(public * com.example.demo.controller.*.**(..))")
    public void serviceFindFunction(){}
    /**
     * 处理controller 中的IdNotEmpty注解
     * @throws Throwable 
     */
    @Around("serviceFindFunction()")
    public Object scanIdNotEmpty(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.debug("进入ControllerParamValidAspect AOP");

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
        
        if(method!=null&&method.getAnnotation(IdNotEmpty.class)!=null){
        	Object obj= param.getSearchParam();
        	//得到所有属性
            Field[] fields = obj.getClass().getDeclaredFields();
            for (int i=0;i<fields.length;i++){//遍历
                    //得到属性
                    Field field = fields[i];
                    //打开私有访问
                    field.setAccessible(true);
                    //获取属性
                    String name = field.getName();
                    if(name.equals("id")){
                    	//获取属性值
                        Object value = field.get(obj);
                        if(StringUtils.isEmpty(value)){
                        	throw new MethodArgumentNotValidException(null, null);
                        }
                    }
                    
            }
        }
        log.debug("接口{}-方法[{}]开始执行...请求参数：{}"
        		,target.getClass().getName()
        		,signature.getName()
        		,param.toString()
        		);
        Object object = proceedingJoinPoint.proceed();
        log.debug("接口{}-方法[{}]执行结束...返回参数：{}"
        		,target.getClass().getName()
        		,signature.getName()
        		,object.toString()
        		);
        if(object instanceof List) {
            List objList = (List) object;
            PageInfo pageInfo = new PageInfo<>(objList);
            return pageInfo;
        }
        return object;
    }
    
    
    
    /**
     * 处理controller 中的IdNotEmpty注解
     * @throws Throwable 
     */
    @Around("serviceFindFunction()")
    public Object scanIsDeletedSetNull(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    	log.debug("进入ControllerParamValidAspect AOP");

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
        
        if(method!=null&&method.getAnnotation(IsDeletedSetNull.class)!=null){
        	Object obj= param.getUpdateParam();
        	//得到所有属性
            Field[] fields = obj.getClass().getSuperclass().getDeclaredFields();
            for (int i=0;i<fields.length;i++){//遍历
                    //得到属性
                    Field field = fields[i];
                    //打开私有访问
                    field.setAccessible(true);
                    //获取属性
                    String name = field.getName();
                    if(name.equals("isDeleted")){
                    	//set  it  null
//                    	field.set(obj, null);
                    	field.setInt(obj, 0);
                        Object value = field.get(obj);
//                        if(StringUtils.isEmpty(value)){
//                        	throw new MethodArgumentNotValidException(null, null);
//                        }
                    }
                    
            }
        }
        log.debug("接口{}-方法[{}]开始执行...请求参数：{}"
        		,target.getClass().getName()
        		,signature.getName()
        		,param.toString()
        		);
        Object object = proceedingJoinPoint.proceed();
        log.debug("接口{}-方法[{}]执行结束...返回参数：{}"
        		,target.getClass().getName()
        		,signature.getName()
        		,object.toString()
        		);
        if(object instanceof List) {
            List objList = (List) object;
            PageInfo pageInfo = new PageInfo<>(objList);
            return pageInfo;
        }
        return object;
    }
    
    

}
