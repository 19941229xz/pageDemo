package com.example.demo.common.util;

import java.util.UUID;


public class UUIDUtil {
	
	/**
	 * @return  a uuid not contain "-"
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	

}
