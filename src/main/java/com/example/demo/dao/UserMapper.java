package com.example.demo.dao;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.common.base.BaseMapper;
import com.example.demo.model.User;

@Mapper
public interface UserMapper extends BaseMapper<User>{

//	@Select("select * from users")
//	List<User> search();
//
//	Object addOne(@Valid User addParam);

	
	
}
