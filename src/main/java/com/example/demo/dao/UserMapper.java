package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.User;

@Mapper
public interface UserMapper {

	@Select("select * from users")
	List<User> findUserList();

	
	
}
