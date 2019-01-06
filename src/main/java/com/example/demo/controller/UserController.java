package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.Page;
import com.example.demo.common.base.BaseReqParam;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findUserList")
    public Object findUserList(@RequestBody BaseReqParam<User> param){
//        return userService.findUserListWithPage( param.getPageParam().getPage(),param.getPageParam().getSize());
        return userService.findUserListWithPage( param);

    }
    
//    @RequestMapping("/findUserList2")
//    public Object findUserList2(@RequestBody UserRequestParam param){
//        return userService.findUserList( param.getPage() , param.getSize());
//
//    }
}
