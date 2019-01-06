package com.example.demo.service;

import com.example.demo.common.base.BaseReqParam;
import com.example.demo.model.User;

public interface UserService {

	
	Object findUserListWithPage(int page ,int pageSize);

	Object findUserList(int page, int size);

	Object findUserListWithPage(BaseReqParam<User> param);
	
}
