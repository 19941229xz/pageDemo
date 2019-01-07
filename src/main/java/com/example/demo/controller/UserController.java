package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.base.BaseController;
import com.example.demo.common.base.BaseReqParam;
import com.example.demo.model.User;
import com.example.demo.service.UserService;


@RestController
@RequestMapping("user")
public class UserController implements BaseController<User>{
    @Autowired
    private UserService userService;

    @PostMapping("/search")
    @Override
    public Object search(@RequestBody BaseReqParam<User> param){
        return userService.findUserListWithPage( param);
    }

	@Override
	public Object addOne(BaseReqParam<User> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object addSome(BaseReqParam<User> param) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
