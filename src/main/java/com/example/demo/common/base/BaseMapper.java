package com.example.demo.common.base;

import java.util.List;

import javax.validation.Valid;


public interface BaseMapper<T> {

	List<T> search();

	int addOne(T addParam);
	
	
}
