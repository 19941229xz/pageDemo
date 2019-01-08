package com.example.demo.common.base;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.example.demo.common.Page;

import lombok.Data;

@Data
public class BaseReqParam<T> {

	/**
	 * 条件查询参数
	 */
	private T searchParam;
	
	/**
	 * 删除数据参数  支持条件删除
	 */
	private T deleteParam;
	
	/**
	 * 修改数据参数 
	 */
	private T updateParam;
	
	/**
	 * 对象新增参数
	 */
	@Valid
	@NotNull(message="请求参数不能为空")
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
	 * 批量新增对象参数  @valid 不支持检查数组里面的对象 如需使用
	 * 此方法 应该增加相关校验逻辑
	 */
	private T[] itemArray;
}
