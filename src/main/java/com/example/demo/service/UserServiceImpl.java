package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserMapper;
import com.example.demo.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    UserMapper userMapper;

	@Override
	public Object findUserListWithPage(int page ,int pageSize) {
		// TODO Auto-generated method stub
		List<User> userList = userMapper.findUserList();
		return userList;
	}

	/**
     * @param page 当前页
     * @param pageSize 每页数量
     * @return
     */
    public PageInfo<User> findUserList(int page,int pageSize){
        //pageHelper帮助生成分页语句
        //底层实现 采用改写语句
        PageHelper.startPage(page,pageSize);
        List<User> userList = userMapper.findUserList();
        PageInfo<User> userListPageInfo = new PageInfo<>(userList);
        return userListPageInfo;
    }

}
