package com.example.demo.common.base;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.model.User;


public interface BaseService<T> {

	/**
	 * 分页查询
	 */
	Object searchWithPage(BaseReqParam<T> param);
	/**
	 * 普通查询
	 */
	List<T> search(T searchParam);
	/**
	 * 添加一个对象 继承此接口  需要使用@valid注解实现 参数的校验
	 */
	Object addOne(@Valid T addParam);
	
	T getOne(T searchParam);

	Object delete(T deleteParam);
	
	Object update(T updateParam);
	
	
}
