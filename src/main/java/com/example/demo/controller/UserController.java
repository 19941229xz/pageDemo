package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.req.UserRequestParam;
import com.example.demo.service.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findUserList")
    public Object findUserList(@RequestBody UserRequestParam param){
        return userService.findUserListWithPage( param.getPage() , param.getSize());

    }
    
    @RequestMapping("/findUserList2")
    public Object findUserList2(@RequestBody UserRequestParam param){
        return userService.findUserList( param.getPage() , param.getSize());

    }
}
