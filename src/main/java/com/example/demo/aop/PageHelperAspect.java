package com.example.demo.aop;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.common.SystemConfig;
import com.example.demo.common.base.BaseReqParam;
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
	
	@Autowired
	private SystemConfig systemConfig;

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
        
        //对象转换
        BaseReqParam<Object> param=(BaseReqParam<Object>)args[0];
        //分页
        pageOpration(param);
        //排序
        sortOpration(param);
        
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
    
    void pageOpration(BaseReqParam<Object> param){
//    	if(param.getPageParam().getSize()==0){
//    		PageHelper.startPage(param.getPageParam().getPage(),param.getPageParam().getSize());
//        }else{
//        	PageHelper.startPage(param.getPageParam().getPage(),param.getPageParam().getSize());
//        }
    	if(param.getPageParam()==null){
    		PageHelper.startPage(0,systemConfig.getDefaultPageSize());
    	}else{
    		int size_param=param.getPageParam().getSize();
        	PageHelper.startPage(param.getPageParam().getPage(),size_param==0?
        			systemConfig.getDefaultPageSize():size_param);
    	}
    	
    }
    
    void sortOpration(BaseReqParam<Object> param){
    	if(param.getSortParam()!=null&&param.getSortParam().length>0){
        	for (int i = 0; i < param.getSortParam().length; i++) {
            	PageHelper.orderBy(param.getSortParam()[i]);
    		}
        }
    }

}
