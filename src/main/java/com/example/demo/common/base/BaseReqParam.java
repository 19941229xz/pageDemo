package com.example.demo.common.base;

import com.example.demo.common.Page;

import lombok.Data;

@Data
public class BaseReqParam<T> {

	private T searchParam;
	
	private Page pageParam;
	
	private String[] sortParam;
}
