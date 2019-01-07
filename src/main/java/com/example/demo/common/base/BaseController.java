package com.example.demo.common.base;

public interface BaseController<T> {

	/**
	 * 查询方法 aop实现了自动分页和排序的功能
	 */
	Object search(BaseReqParam<T> param);
	
	/**
	 * 新增一个 对象的方法  继承此方法的子类  需要使用@valid注解实现 参数的校验
	 */
	Object addOne(BaseReqParam<T> param);
	
	/**
	 * 批量新增对象的方法  继承此方法的子类  需要使用校验每一个对象包含的参数
	 */
	Object addSome(BaseReqParam<T> param);
}
