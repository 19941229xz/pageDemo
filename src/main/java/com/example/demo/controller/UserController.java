package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.annotation.IdNotEmpty;
import com.example.demo.common.annotation.IsDeletedSetNull;
import com.example.demo.common.base.BaseController;
import com.example.demo.common.base.BaseReqParam;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

@Api("UserController相关api")
@RestController
@RequestMapping("user")
public class UserController implements BaseController<User>{
    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询user的信息",notes = "")
//    @ApiImplicitParam(name ="username",value = "账号",paramType = "path",required = true,dataType = "String")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "用户id",dataType = "String",paramType = "query",example = "1112")
//    })
//    @ApiResponses({
//            @ApiResponse(code=400,message = "请求参数没有填好"),
//            @ApiResponse(code=404,message="请求路径没有找到")
//    })        
    @PostMapping("/search")
    @Override
    public Object search(@RequestBody BaseReqParam<User> param){
        return userService.searchWithPage( param);
    }

    @PostMapping("/addOne")
	@Override
	public Object addOne(@RequestBody @Valid BaseReqParam<User> param) {
		// TODO Auto-generated method stub
		return userService.addOne((User)param.getAddParam());
	}

    @ApiOperation(value = "根据id获取user",notes = "只需要在addParam中添加id")
    @PostMapping("/getOne")
    @IdNotEmpty
	@Override
	public Object getOne(@RequestBody BaseReqParam<User> param) {
		// TODO Auto-generated method stub
		return userService.getOne((User)param.getSearchParam());
	}

    @ApiOperation(value = "条件逻辑删除user",notes = "只需要在deleteParam中添加 相关筛选条件")
    @PostMapping("/delete")
	@Override
	public Object delete(@RequestBody BaseReqParam<User> param) {
		// TODO Auto-generated method stub
		return userService.delete((User)param.getDeleteParam());
	}

    @IsDeletedSetNull
    @ApiOperation(value = "条件修改删除user",notes = "只需要在deleteParam中添加 相关筛选条件")
    @PostMapping("/update")
	@Override
	public Object update(@RequestBody BaseReqParam<User> param) {
		// TODO Auto-generated method stub
		return userService.update((User)param.getUpdateParam());
	}
	
	
	
	
    
}
