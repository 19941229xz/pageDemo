package com.example.demo.common.util;

import java.lang.reflect.Field;

import com.example.demo.common.HttpCode;
import com.example.demo.common.HttpException;

public class AopUtil {

	
	public static void setFieldValue(Object obj,String key,Object value) throws IllegalArgumentException, IllegalAccessException{
		if(obj==null) {
			return ;
		}
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
	
	public static Object getFieldValue(Object obj,String key) throws Exception{
    	Field[] fields = obj.getClass().getDeclaredFields();
    	Object value=null;
        for (int j=0;j<fields.length;j++){//遍历
                //得到属性
                Field field = fields[j];
                //打开私有访问
                field.setAccessible(true);
                //获取属性
                String name = field.getName();
                
                
                if(name.equals(key)){
                	//获取属性的值
                	value=field.get(obj);
                    return value;
                }else {
                	throw new HttpException().setCode(HttpCode.ITEM_NOT_FOUND).setMsg("该属性找不到");
                }
        }
		return value;
        
    }
	
	
	
}
