package com.example.demo.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 
 * @author xiongzh
 * 加上此注解 参数中的id 都不能为空
 */
@Documented
@Target(java.lang.annotation.ElementType.METHOD)
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Inherited
public @interface IdNotEmpty {

}
