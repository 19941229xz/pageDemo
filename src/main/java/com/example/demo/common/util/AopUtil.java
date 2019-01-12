package com.example.demo.common.util;

import java.lang.reflect.Field;

public class AopUtil {

	
	public static void setFieldValue(Object obj,String key,Object value) throws IllegalArgumentException, IllegalAccessException{
    	Field[] fields = obj.getClass().getDeclaredFields();
        for (int j=0;j<fields.length;j++){//遍历
                //得到属性
                Field field = fields[j];
                //打开私有访问
                field.setAccessible(true);
                //获取属性
                String name = field.getName();
                if(name.equals(key)){
                	field.set(obj, value);
                }
        }
        
    }
	
	
	
}
