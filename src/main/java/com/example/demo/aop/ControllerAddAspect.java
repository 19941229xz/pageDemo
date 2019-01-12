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
public class ControllerAddAspect {
	
	@Autowired
	private SystemConfig systemConfig;
	
	// 拦截 controller addOne 所有方法
    @Pointcut("execution(public * com.example.demo.controller.*.addOne(..))")
    public void addFindFunction(){}
    
    // 拦截 controller addSome 所有方法
    @Pointcut("execution(public * com.example.demo.controller.*.addSome(..))")
    public void addSomeFindFunction(){}
    
    /**
     * 处理controller 中的IdNotEmpty注解
     * @throws Throwable 
     */
//    @Around("addSomeFindFunction()")
//    public Object processAddSome(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        log.debug("进入ControllerParamValidAspect AOP");
//
//        //获取连接点方法运行时的入参列表
//        Object[] args = proceedingJoinPoint.getArgs();
//
//        //获取连接点的方法签名对象 和被拦截的方法
//        Signature signature = proceedingJoinPoint.getSignature();
//        MethodSignature methodSignature = (MethodSignature) signature;  
//        Method method = methodSignature.getMethod();
//        
//        //获取连接点所在的类的对象(实例)
//        Object target = proceedingJoinPoint.getTarget(); 
//        
//        //对象转换
//        BaseReqParam<Object> param=(BaseReqParam<Object>)args[0];
//        
//        if(method!=null&&method.getAnnotation(IdNotEmpty.class)!=null){
//        	Object obj= param.getSearchParam();
//        	//得到所有属性
//            Field[] fields = obj.getClass().getDeclaredFields();
//            for (int i=0;i<fields.length;i++){//遍历
//                    //得到属性
//                    Field field = fields[i];
//                    //打开私有访问
//                    field.setAccessible(true);
//                    //获取属性
//                    String name = field.getName();
//                    if(name.equals("id")){
//                    	//获取属性值
//                        Object value = field.get(obj);
//                        if(StringUtils.isEmpty(value)){
//                        	throw new MethodArgumentNotValidException(null, null);
//                        }
//                    }
//                    
//            }
//        }
//        log.debug("接口{}-方法[{}]开始执行...请求参数：{}"
//        		,target.getClass().getName()
//        		,signature.getName()
//        		,param.toString()
//        		);
//        Object object = proceedingJoinPoint.proceed();
//        log.debug("接口{}-方法[{}]执行结束...返回参数：{}"
//        		,target.getClass().getName()
//        		,signature.getName()
//        		,object.toString()
//        		);
//        if(object instanceof List) {
//            List objList = (List) object;
//            PageInfo pageInfo = new PageInfo<>(objList);
//            return pageInfo;
//        }
//        return object;
//    }
    
    
    
    /**
     * 处理controller 中的 add param
     * @throws Throwable 
     */
    @Around("addFindFunction()")
    public Object processAddParam(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    	log.debug("进入ControllerAddAspect AOP");

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
        
        // add param  ieDeleted  must be 0 
        Object obj= param.getAddParam();
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
    
    	
    	
    
    
//    void setFieldValue(Object obj,String key,Object value) throws IllegalArgumentException, IllegalAccessException{
//    	Field[] fields = obj.getClass().getDeclaredFields();
//        for (int j=0;j<fields.length;j++){//遍历
//                //得到属性
//                Field field = fields[j];
//                //打开私有访问
//                field.setAccessible(true);
//                //获取属性
//                String name = field.getName();
//                if(name.equals(key)){
//                	field.set(obj, value);
//                }
//        }
//        
//    }
    

}
