package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.base.BaseController;
import com.example.demo.common.base.BaseReqParam;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value="UserController相关api",description="用户接口详细描述信息")
@RestController
@RequestMapping("user")
public class UserController implements BaseController<User>{

	@Autowired
    private UserService userService;

	@ApiOperation(value = "查询用户的信息",notes = "在searchParam中添加用户的各种属性作为查询条件")
    @PostMapping("/search")
    @Override
    public Object search(@RequestBody BaseReqParam<User> param){
        return userService.searchWithPage(param);
    }

    @ApiOperation(value = "添加用户信息",notes = "需要在addParam中添加完整用户信息")
    @PostMapping("/addOne")
	@Override
	public Object addOne(@RequestBody @Valid BaseReqParam<User> param) {
		return userService.addOne((User)param.getAddParam());
	}

    @ApiOperation(value = "根据id获取用户",notes = "只需要在searchParam中添加id")
    @PostMapping("/getOne")
	@Override
	public Object getOne(@RequestBody BaseReqParam<User> param) {
		return userService.getOne((User)param.getSearchParam());
	}

    @ApiOperation(value = "条件逻辑删除用户",notes = "只需要在deleteParam中添加 相关筛选条件")
    @PostMapping("/delete")
	@Override
	public Object delete(@RequestBody BaseReqParam<User> param) {
		return userService.delete((User)param.getDeleteParam());
	}

    @ApiOperation(value = "条件修改用户",notes = "只需要在updateParam中添加 ")
    @PostMapping("/update")
	@Override
	public Object update(@RequestBody BaseReqParam<User> param) {
		return userService.update((User)param.getUpdateParam());
	}
	
}

