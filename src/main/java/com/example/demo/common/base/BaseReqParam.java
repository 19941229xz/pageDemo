package com.example.demo.common.base;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.example.demo.common.Page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="封装公共参数",description="封装公共参数")
public class BaseReqParam<T> {

	/**
	 * 条件查询参数
	 */
	@ApiModelProperty(value="条件查询参数",name="searchParam")
	private T searchParam;
	
	/**
	 * 删除数据参数  支持条件删除
	 */
	@ApiModelProperty(value="删除数据参数",name="deleteParam")
	private T deleteParam;
	
	/**
	 * 修改数据参数 
	 */
	@ApiModelProperty(value="修改数据参数",name="updateParam")
	private T updateParam;
	
	/**
	 * 对象新增参数
	 */
	@Valid
	@NotNull(message="请求参数不能为空")
	@ApiModelProperty(value="对象新增参数",name="addParam")
	private T addParam;
	
	/**
	 * 分页参数
	 */
	@ApiModelProperty(value="对象新增参数",name="addParam") 	
	private Page pageParam;
	
	/**
	 * 排序参数
	 */
	@ApiModelProperty(value="排序参数",name="sortParam") 
	private String[] sortParam;
	
	/**
	 * 批量新增对象参数  @valid 不支持检查数组里面的对象 如需使用
	 * 此方法 应该增加相关校验逻辑
	 */
	@ApiModelProperty(value="批量新增对象参数",name="itemArray") 
	private T[] itemArray;
}
