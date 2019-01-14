package com.example.demo.common.base;


public interface BaseController<T> {

	/**
	 * 查询方法 aop实现了自动分页和排序的功能
	 */
	Object search(BaseReqParam<T> param);
	
	/**
	 * 查询方法 aop实现了自动分页和排序的功能
	 */
	Object getOne(BaseReqParam<T> param);
	
	/**
	 * 新增一个 对象的方法  继承此方法的子类  需要使用@valid注解实现 参数的校验
	 */
	Object addOne(BaseReqParam<T> param);
	
	/**
	 * 删除的方法  aop 实现统一对id的非空校验
	 */
	Object delete(BaseReqParam<T> param);
	
	/**
	 * 删除的方法  aop 实现统一对id的非空校验
	 */
//	@ApiOperation(value = "条件修改user",notes = "只需要在updateParam中添加 相关筛选条件")
	Object update(BaseReqParam<T> param);
}
