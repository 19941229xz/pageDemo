package com.example.demo.common.base;

import javax.validation.Valid;

import com.example.demo.common.Page;

import lombok.Data;

@Data
public class BaseReqParam<T> {

	/**
	 * 条件查询参数
	 */
	private T searchParam;
	
	/**
	 * 对象新增参数
	 */
	@Valid
	private T addParam;
	
	/**
	 * 分页参数
	 */
	private Page pageParam;
	
	/**
	 * 排序参数
	 */
	private String[] sortParam;
	
	/**
	 * 批量新增对象参数
	 */
	private T[] itemArray;
}
