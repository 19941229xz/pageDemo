package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.base.BaseReqParam;
import com.example.demo.dao.UserMapper;
import com.example.demo.model.User;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    UserMapper userMapper;

	@Override
	public Object searchWithPage(BaseReqParam<User> param) {
		List<User> userList = userMapper.search((User)param.getSearchParam());
		return userList;
	}

	@Override
	public Object addOne(User addParam) {
		return userMapper.addOne(addParam);
	}

	@Override
	public User getOne(User searchParam) {
		return userMapper.getOne(searchParam);
	}


	@Override
	public Object delete(User deleteParam) {
		return userMapper.delete(deleteParam);
	}

	@Override
	public Object update(User updateParam) {
		return userMapper.update(updateParam);
	}



}