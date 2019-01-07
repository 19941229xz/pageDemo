package com.example.demo.common.base;

import javax.validation.Valid;


public interface BaseService<T> {

	/**
	 * 分页查询
	 */
	Object searchWithPage(BaseReqParam<T> param);

	/**
	 * 添加一个对象 继承此接口  需要使用@valid注解实现 参数的校验
	 */
	Object addOne(@Valid T addParam);
	
}
