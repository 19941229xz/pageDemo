package com.example.demo.common.base;

import java.util.List;

import javax.validation.Valid;


public interface BaseMapper<T> {

	List<T> search(T searchParam);
	
	T getOne(T searchParam);

	int addOne(T addParam);
	
	int delete(T deleteParam);
	
	int update(T updateParam);
	
	
}
