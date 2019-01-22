package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.auth.JwtService;
import com.example.demo.common.base.BaseReqParam;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api("LoginController相关api")
@RestController
@RequestMapping("login")
public class LoginController{

	@Autowired
    private UserService userService;
	
	@Autowired
    private JwtService jwtService;

	@ApiOperation(value = "/user",notes = "普通登陆")
    @PostMapping("/user")
    public Object user(@RequestBody BaseReqParam<User> param){
		
        return jwtService.sign("tom", "123");
    }

	
}

